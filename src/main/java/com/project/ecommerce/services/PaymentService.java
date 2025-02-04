package com.project.ecommerce.services;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.project.ecommerce.entities.Order;
import com.project.ecommerce.entities.PaymentOrders;
import com.project.ecommerce.entities.User;
import com.project.ecommerce.exceptions.PaymentOrderException;
import com.project.ecommerce.repository.OrderRepository;
import com.project.ecommerce.repository.PaymentOrderRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentOrderRepository paymentOrderRepository;

    private final OrderRepository orderRepository;

    // private String apisecret = "apisecret";
    private String stripeSecretkey = "stripesecretkey";

    public PaymentOrders createOrder(User user, Set<Order> orders) {
        Long amount = orders.stream().mapToLong(Order::getTotalSellingPrice).sum();

        PaymentOrders paymentOrder = new PaymentOrders();
        paymentOrder.setAmount(amount);
        paymentOrder.setUser(user);
        paymentOrder.setOrders(orders);

        return paymentOrderRepository.save(paymentOrder);
    }

    public PaymentOrders getPaymentOrderById(Long id) throws PaymentOrderException {

        return paymentOrderRepository.findById(id)
                .orElseThrow(() -> new PaymentOrderException("payment order not found"));
    }

    public PaymentOrders getPaymentOrderByPaymentId(Long id) throws PaymentOrderException {
        PaymentOrders paymentOrder = paymentOrderRepository.findByPaymentLinkId(id);

        if (paymentOrder == null) {
            throw new PaymentOrderException("payment order not found with provided payment link id");
        }
        return paymentOrder;
    }

    public Boolean proceedPaymentOrder(PaymentOrders paymentOrder, Long paymentId, Long paymentLinkId) {

        return false;
    }

    public String createStripePaymentLink(User user, Long amount, Long orderId) throws StripeException {

        Stripe.apiKey = stripeSecretkey;
        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:3000/payment-success" + orderId)
                .setCancelUrl("http://localhost:3000/payment-cancel")
                .addLineItem(SessionCreateParams.LineItem.builder()
                        .setQuantity(1L)
                        .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                                .setCurrency("usd")
                                .setUnitAmount(amount * 100)
                                .setProductData(
                                        SessionCreateParams.LineItem.PriceData.ProductData
                                                .builder().setName("SwiftCart Payment")
                                                .build())
                                .build()

                        ).build()

                )
                .build();

        Session session = Session.create(params);
        return session.getUrl();

    }
}

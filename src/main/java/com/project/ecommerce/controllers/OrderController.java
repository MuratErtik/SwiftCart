package com.project.ecommerce.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.config.JwtProvider;
import com.project.ecommerce.domain.PaymentMethod;
import com.project.ecommerce.entities.Address;
import com.project.ecommerce.entities.Cart;
import com.project.ecommerce.entities.Order;
import com.project.ecommerce.entities.OrderItem;
import com.project.ecommerce.entities.PaymentOrders;
import com.project.ecommerce.entities.Seller;
import com.project.ecommerce.entities.SellerReport;
import com.project.ecommerce.entities.User;
import com.project.ecommerce.exceptions.OrderException;
import com.project.ecommerce.responses.PaymentLinkResponse;
import com.project.ecommerce.services.CartService;
import com.project.ecommerce.services.OrderService;
import com.project.ecommerce.services.PaymentService;
import com.project.ecommerce.services.SellerReportService;
import com.project.ecommerce.services.SellerService;
import com.project.ecommerce.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    private final UserService userService;

    private final CartService cartService;

    private final JwtProvider jwtProvider;

    private final SellerService sellerService;

    private final SellerReportService sellerReportService;

    private final PaymentService paymentService;

    

    @PostMapping
    public ResponseEntity<PaymentLinkResponse> createOrderHandler(@RequestBody Address shippingAddress,
            @RequestParam PaymentMethod paymentMethod,
            @RequestHeader("Authorization") String jwt) throws Exception {

        String email = jwtProvider.getEmailFromJwtToken(jwt);

        User user = userService.findUserByEmail(email);

        Cart cart = cartService.findUserCart(user);

        Set<Order> orders = orderService.createOrder(user, shippingAddress, cart);

        PaymentOrders paymentOrder = paymentService.createOrder(user, orders);

        PaymentLinkResponse res = new PaymentLinkResponse();

        if (paymentMethod.equals(PaymentMethod.STRIPE)) {
            String paymentUrl = paymentService.createStripePaymentLink(user, paymentOrder.getAmount(),paymentOrder.getId());
            res.setPayment_link_url(paymentUrl);
            
        }

        return new ResponseEntity<>(res, HttpStatus.OK);

    }

    @GetMapping("/user")
    public ResponseEntity<List<Order>> userOrderHistoryHandler(@RequestHeader("Authorization") String jwt)
            throws Exception {

        String email = jwtProvider.getEmailFromJwtToken(jwt);

        User user = userService.findUserByEmail(email);

        List<Order> orders = orderService.userOrderHistory(user.getId());

        return new ResponseEntity<>(orders, HttpStatus.ACCEPTED);

    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@RequestHeader("Authorization") String jwt, @PathVariable Long orderId) throws Exception{

        // String email = jwtProvider.getEmailFromJwtToken(jwt);

        // User user = userService.findUserByEmail(email);

        Order order = orderService.findOrderById(orderId);

        return new ResponseEntity<>(order,HttpStatus.ACCEPTED);

        
        
    }

    @GetMapping("/item/{orderItemId}")
    public ResponseEntity<OrderItem> getOrderItemById(@RequestHeader("Authorization") String jwt, @PathVariable Long orderItemId) throws OrderException{

        OrderItem orderItem = orderService.findOrderItemById(orderItemId);

        return new ResponseEntity<>(orderItem,HttpStatus.ACCEPTED);
             
    }

    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<Order> cancelOrder(@PathVariable Long orderId,@RequestHeader("Authorization") String jwt) throws Exception{

        String email = jwtProvider.getEmailFromJwtToken(jwt);
        User user = userService.findUserByEmail(email);

        Order order = orderService.cancelOrder(orderId, user);

        Seller seller = sellerService.getSellerById(order.getSellerId());

        SellerReport sellerReport = sellerReportService.getSellerReport(seller);

        sellerReport.setCanceledOrders(sellerReport.getCanceledOrders()+1);
        sellerReport.setTotalRefunds(sellerReport.getTotalRefunds()+order.getTotalSellingPrice());
        sellerReportService.updateSellerReport(sellerReport);

        return new ResponseEntity<>(order,HttpStatus.OK);
        

    }

    















}

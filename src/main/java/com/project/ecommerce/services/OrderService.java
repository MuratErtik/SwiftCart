package com.project.ecommerce.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.ecommerce.domain.OrderStatus;
import com.project.ecommerce.domain.PaymentStatus;
import com.project.ecommerce.entities.Address;
import com.project.ecommerce.entities.Cart;
import com.project.ecommerce.entities.CartItem;
import com.project.ecommerce.entities.Order;
import com.project.ecommerce.entities.OrderItem;
import com.project.ecommerce.entities.User;
import com.project.ecommerce.exceptions.OrderException;
import com.project.ecommerce.repository.AddressRepository;
import com.project.ecommerce.repository.OrderItemRepository;
import com.project.ecommerce.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final AddressRepository addressRepository;
    private final OrderItemRepository orderItemRepository;

    public Set<Order> createOrder(User user, Address shippingAddress, Cart cart) {

        if (!user.getAddresses().contains(shippingAddress)) {
            user.getAddresses().add(shippingAddress);
        }
        Address address = addressRepository.save(shippingAddress);

        Map<Long, List<CartItem>> itemsBySellers = cart.getCartItems().stream()
                .collect(Collectors.groupingBy(item -> item.getProduct()
                        .getSeller().getId()));

        Set<Order> orders = new HashSet<>();

        // for one of each seller
        for (Map.Entry<Long, List<CartItem>> entry : itemsBySellers.entrySet()) {
            Long sellerId = entry.getKey();
            List<CartItem> items = entry.getValue();

            int totalOrderPrice = items.stream().mapToInt(
                    CartItem::getSellingPrice).sum();

            int totalItem = items.stream().mapToInt(
                    CartItem::getQuantity).sum();

            Order createdOrder = new Order();
            createdOrder.setUser(user);
            createdOrder.setSellerId(sellerId);
            createdOrder.setTotalMrpPrice(totalOrderPrice);
            createdOrder.setTotalSellingPrice(totalOrderPrice);
            createdOrder.setShippingAddress(address);
            createdOrder.setOrderStatus(OrderStatus.PENDING);
            createdOrder.setTotalItem(totalItem);
            createdOrder.getPaymentDetails().setStatus(PaymentStatus.PENDING);

            Order savedOrder = orderRepository.save(createdOrder);
            orders.add(savedOrder);

            List<OrderItem> orderItems = new ArrayList<>();
            for (CartItem item : items) {
                OrderItem orderItem = new OrderItem();
                orderItem.setOrder(savedOrder);
                orderItem.setMrpPrice(item.getMrpPrice());
                orderItem.setProduct(item.getProduct());
                orderItem.setQuantity(item.getQuantity());
                orderItem.setSize(item.getSize());
                orderItem.setUserId(item.getUserId());
                orderItem.setSelleingPrice(item.getSellingPrice());
                savedOrder.getOrderItems().add(orderItem);

                OrderItem savedOrderItem = orderItemRepository.save(orderItem);
                orderItems.add(savedOrderItem);
            }
        }
        /*
         * Sepetteki ürünleri satıcılara göre ayırıyor.
         * Her satıcı için ayrı bir sipariş oluşturuyor.
         * Siparişlerin içine ilgili ürünleri ekliyor.
         * Son olarak, oluşturulan siparişleri kullanıcıya döndürüyor.
         */
        return orders;

    }

    public Order findOrderById(Long id) throws OrderException{
        return orderRepository.findById(id).orElseThrow(() -> new OrderException("order not found with id -> "+id));
    }

    public List<Order> userOrderHistory(Long userId){
        return orderRepository.findByUserId(userId);
    }

    public List<Order> sellerOrderHistory(Long sellerId){
        return orderRepository.findByUserId(sellerId);
    }

    public Order updateOrderStatus(Long orderId, OrderStatus orderStatus) throws OrderException{

        Order order = this.findOrderById(orderId);

        order.setOrderStatus(orderStatus);

        return orderRepository.save(order);

    }

    public Order cancelOrder(Long userId,User user) throws Exception{

        Order order = this.findOrderById(userId);

        if (!user.getId().equals(order.getUser().getId())) {
            throw new Exception("you do not have access to this order!");
        }

        order.setOrderStatus(OrderStatus.CANCELLED);

        return orderRepository.save(order);


    }

    public OrderItem findById(Long id) throws OrderException{

        return orderItemRepository.findById(id).orElseThrow(() -> new OrderException("Order item does not exist!"));
    }
}

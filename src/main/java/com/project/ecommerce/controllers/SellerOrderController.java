package com.project.ecommerce.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.config.JwtProvider;
import com.project.ecommerce.domain.OrderStatus;
import com.project.ecommerce.entities.Order;
import com.project.ecommerce.entities.Seller;
import com.project.ecommerce.exceptions.OrderException;
import com.project.ecommerce.exceptions.SellerException;
import com.project.ecommerce.services.OrderService;
import com.project.ecommerce.services.SellerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/seller/orders")
public class SellerOrderController {

    private final SellerService sellerService;
    private final OrderService orderService;
    private final JwtProvider jwtProvider;

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrdersHandler(@RequestHeader("Authorization") String jwt)
            throws SellerException {

        String email = jwtProvider.getEmailFromJwtToken(jwt);
        Seller seller = sellerService.getSellerByEmail(email);

        List<Order> orders = orderService.sellerOrderHistory(seller.getId());

        return new ResponseEntity<>(orders, HttpStatus.ACCEPTED);

    }

    @PatchMapping("/{orderId}/status/{orderStatus}")
    public ResponseEntity<Order> updateOrderHandler(@PathVariable Long orderId, @PathVariable OrderStatus orderStatus,
            @RequestHeader("Authorization") String jwt) throws OrderException {

        Order order = orderService.updateOrderStatus(orderId, orderStatus);

        return new ResponseEntity<>(order, HttpStatus.ACCEPTED);
    }

    
}

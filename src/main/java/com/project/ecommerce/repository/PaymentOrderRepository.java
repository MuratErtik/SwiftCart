package com.project.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ecommerce.entities.PaymentOrders;

public interface PaymentOrderRepository extends JpaRepository<PaymentOrders,Long> {

    PaymentOrders findByPaymentLinkId(Long paymentId);
}

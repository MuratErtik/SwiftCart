package com.project.ecommerce.entities;

import com.project.ecommerce.domain.PaymentStatus;

import lombok.Data;

@Data
public class PaymentDetails {
    private String paymentId;

    private PaymentStatus status;

}

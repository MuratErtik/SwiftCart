package com.project.ecommerce.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.config.JwtProvider;
import com.project.ecommerce.entities.Order;
import com.project.ecommerce.entities.PaymentOrders;
import com.project.ecommerce.entities.Seller;
import com.project.ecommerce.entities.SellerReport;
import com.project.ecommerce.entities.User;
import com.project.ecommerce.responses.ApiResponse;
import com.project.ecommerce.responses.PaymentLinkResponse;
import com.project.ecommerce.services.PaymentService;
import com.project.ecommerce.services.SellerReportService;
import com.project.ecommerce.services.SellerService;
import com.project.ecommerce.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;
    private final JwtProvider jwtProvider;
    private final UserService userService;
    private final SellerService sellerService;
    private final SellerReportService sellerReportService;

    @GetMapping("/{paymentId}")
    public ResponseEntity<ApiResponse> paymentSuccessHandler(@PathVariable Long paymentId,
            @RequestHeader("Authorization") String jwt, @RequestParam Long paymentLinkId) throws Exception {

        String email = jwtProvider.getEmailFromJwtToken(jwt);
        User user = userService.findUserByEmail(email);

        PaymentLinkResponse paymentLinkResponse;

        PaymentOrders paymentOrders = paymentService.getPaymentOrderById(paymentLinkId);

        boolean paymentSuccess = paymentService.proceedPaymentOrder(paymentOrders, paymentId, paymentLinkId);

        if (paymentSuccess) {
            for (Order order : paymentOrders.getOrders()) {
                Seller seller = sellerService.getSellerById(order.getSellerId());
                SellerReport report = sellerReportService.getSellerReport(seller);
                report.setTotalOrders(report.getTotalOrders() + 1);
                report.setTotalEarnings(report.getTotalEarnings() + order.getTotalSellingPrice());
                report.setTotalSales(report.getTotalSales() + order.getOrderItems().size());
                sellerReportService.updateSellerReport(report);
            }
        }

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Payment Success");
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);

    }

}

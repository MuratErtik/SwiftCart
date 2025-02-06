package com.project.ecommerce.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.domain.AccountStatus;
import com.project.ecommerce.entities.Seller;
import com.project.ecommerce.exceptions.SellerException;
import com.project.ecommerce.services.SellerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AdminController {

    private final SellerService sellerService;

    @PatchMapping("/seller/{sellerId}/status/{status}")
    public ResponseEntity<Seller> updateSellerStatus(@PathVariable long sellerId,@PathVariable AccountStatus status) throws SellerException{

        Seller sellerToUpdate = sellerService.updateSellerAccountStatus(sellerId, status);

        return new ResponseEntity<>(sellerToUpdate,HttpStatus.OK);
    }




}

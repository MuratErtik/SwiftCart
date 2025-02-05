package com.project.ecommerce.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.config.JwtProvider;
import com.project.ecommerce.entities.Seller;
import com.project.ecommerce.entities.Transaction;
import com.project.ecommerce.exceptions.SellerException;
import com.project.ecommerce.services.SellerService;
import com.project.ecommerce.services.TransactionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;
    private final SellerService sellerService;
    private final JwtProvider jwtProvider;


    @GetMapping("/seller")
    public ResponseEntity<List<Transaction>> getTransactionBySeller(@RequestHeader("Authorization") String jwt) throws SellerException{

        String email = jwtProvider.getEmailFromJwtToken(jwt);
        Seller seller = sellerService.getSellerByEmail(email);

        List<Transaction> transactions = transactionService.getTransactionsBySellerId(seller);

        return new ResponseEntity<>(transactions,HttpStatus.OK);



    }

    @GetMapping("/seller")
    public ResponseEntity<List<Transaction>> getAllTransactions(@RequestHeader("Authorization") String jwt){
        List<Transaction> transactions = transactionService.getAllTransaction();
        return new ResponseEntity<>(transactions,HttpStatus.OK);
    }
}

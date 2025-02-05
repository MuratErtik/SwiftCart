package com.project.ecommerce.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.ecommerce.entities.Order;
import com.project.ecommerce.entities.Seller;
import com.project.ecommerce.entities.Transaction;
import com.project.ecommerce.exceptions.SellerException;
import com.project.ecommerce.repository.SellerRepository;
import com.project.ecommerce.repository.TransactionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService {

        private final TransactionRepository transactionRepository;
        private final SellerRepository sellerRepository;

        public Transaction createTransaction(Order order) throws SellerException{

            Seller seller = sellerRepository.findById(order.getSellerId()).orElseThrow(() -> new SellerException("Not found"));

            Transaction transaction = new Transaction();

            transaction.setSeller(seller);
            transaction.setCustomer(order.getUser());
            transaction.setOrder(order);
            transaction.setDate(LocalDateTime.now());

            return transactionRepository.save(transaction);
        }

        public List<Transaction> getTransactionsBySellerId(Seller seller){
            return transactionRepository.findBySellerId(seller.getId());
        }

        public List<Transaction> getAllTransaction(){
            return transactionRepository.findAll();
        }
}

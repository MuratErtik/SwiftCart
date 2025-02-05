package com.project.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ecommerce.entities.Transaction;

public interface TransactionRepository  extends JpaRepository<Transaction,Long>{
    List<Transaction> findBySellerId(Long sellerId);

}

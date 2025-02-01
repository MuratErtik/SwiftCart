package com.project.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ecommerce.domain.AccountStatus;
import com.project.ecommerce.entities.Seller;

public interface SellerRepository extends JpaRepository<Seller,Long> {

    Seller findByEmail(String email);

    List<Seller> findByAccountStatus(AccountStatus status);

}

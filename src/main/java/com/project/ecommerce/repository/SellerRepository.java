package com.project.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ecommerce.entities.Seller;

public interface SellerRepository extends JpaRepository<Seller,Long> {

    Seller findByEmail(String email);

}

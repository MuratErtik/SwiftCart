package com.project.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ecommerce.entities.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {

    

}

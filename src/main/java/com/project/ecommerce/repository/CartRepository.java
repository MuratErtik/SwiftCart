package com.project.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ecommerce.entities.Cart;

public interface CartRepository extends JpaRepository<Cart,Long> {

    Cart findByUserId(Long userId);

}

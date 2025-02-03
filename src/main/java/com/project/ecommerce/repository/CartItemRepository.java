package com.project.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ecommerce.entities.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {

}

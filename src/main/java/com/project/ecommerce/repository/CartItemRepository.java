package com.project.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ecommerce.entities.Cart;
import com.project.ecommerce.entities.CartItem;
import com.project.ecommerce.entities.Product;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    
    CartItem findByCartAndProductAndSize(Cart cart,Product product, String size);

}

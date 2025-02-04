package com.project.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ecommerce.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long>{
    

}

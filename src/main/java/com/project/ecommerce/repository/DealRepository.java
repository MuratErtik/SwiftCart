package com.project.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ecommerce.entities.Deal;

public interface DealRepository extends JpaRepository<Deal,Long> {

    
}

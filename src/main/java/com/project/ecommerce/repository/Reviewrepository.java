package com.project.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ecommerce.entities.Review;

public interface Reviewrepository extends JpaRepository<Review,Long> {

    List<Review> findByProductId(Long productId);
}

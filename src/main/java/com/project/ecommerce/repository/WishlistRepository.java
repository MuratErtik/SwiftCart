package com.project.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ecommerce.entities.WishList;

public interface WishlistRepository extends JpaRepository<WishList,Long> {

    WishList findByUserId(Long userId);

}

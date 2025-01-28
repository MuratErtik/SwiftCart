package com.project.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ecommerce.entities.User;

public interface UserRepository extends JpaRepository<User,Long> {
    
}

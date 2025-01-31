package com.project.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ecommerce.entities.Verification;

public interface VerificationRepository  extends JpaRepository<Verification,Long>{
    Verification findByEmail(String email);

}

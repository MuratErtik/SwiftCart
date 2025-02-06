package com.project.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ecommerce.entities.Coupon;

public interface CouponRepository extends JpaRepository<Coupon,Long> {

    Coupon findByCode(String code);

}

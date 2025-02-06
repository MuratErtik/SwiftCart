package com.project.ecommerce.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.config.JwtProvider;
import com.project.ecommerce.entities.Cart;
import com.project.ecommerce.entities.Coupon;
import com.project.ecommerce.entities.User;
import com.project.ecommerce.exceptions.CouponException;
import com.project.ecommerce.services.CouponService;
import com.project.ecommerce.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/coupons")
public class CouponController {

    private final CouponService couponService;
    private final UserService userService;
    private final JwtProvider jwtProvider;

    @PostMapping("/apply")
    public ResponseEntity<Cart> applyCoupon(@RequestParam String apply, @RequestParam String code,
            @RequestParam Long orderValue, @RequestHeader("Authorization") String jwt) throws Exception {

        String email = jwtProvider.getEmailFromJwtToken(jwt);
        User user = userService.findUserByEmail(email);

        Cart cart;

        if (apply.equals("true")) {
            cart = couponService.applyCoupon(code, orderValue, user);
        } else {
            cart = couponService.removeCoupon(code, user);
        }

        return new ResponseEntity<>(cart, HttpStatus.OK);

    }

    @PostMapping("/admin/create")
    public ResponseEntity<Coupon> createCoupon(@RequestBody Coupon coupon){
        Coupon couponToCreate  = couponService.createCoupon(coupon);
        return new ResponseEntity<>(couponToCreate,HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<?> deleteCoupon(@PathVariable Long id) throws CouponException{
        couponService.deleteCoupon(id);

        return new ResponseEntity<>("Coupon deleted successfully!",HttpStatus.OK);
    }

    @GetMapping("/admin/all")
    public ResponseEntity<List<Coupon>> getAllCoupon(){
        List<Coupon> coupons = couponService.findAllCoupon();
        return new ResponseEntity<>(coupons,HttpStatus.OK);
    }
}


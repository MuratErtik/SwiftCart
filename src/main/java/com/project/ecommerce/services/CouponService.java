package com.project.ecommerce.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.project.ecommerce.entities.Cart;
import com.project.ecommerce.entities.Coupon;
import com.project.ecommerce.entities.User;
import com.project.ecommerce.exceptions.CouponException;
import com.project.ecommerce.repository.CartRepository;
import com.project.ecommerce.repository.CouponRepository;
import com.project.ecommerce.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;

    private final CartRepository cartRepository;

    private final UserRepository userRepository;

    public Cart applyCoupon(String code, double orderValue, User user) throws CouponException{

        Coupon coupon = couponRepository.findByCode(code);

        Cart cart = cartRepository.findByUserId(user.getId());

        if (coupon==null) {

            throw new CouponException("Coupon has not been find");

        }
        if (user.getUsedCoupons().contains(coupon)) {

            throw new CouponException("This coupon has been used already!");

        }
        if (orderValue<coupon.getMinumumOrderValue()) {

            throw new CouponException("Cart amount is insufficient to use a coupon.");

        }
        if (coupon.isActive()&&LocalDate.now().isAfter(coupon.getValidityStartDate()) && LocalDate.now().isBefore(coupon.getValidityEndDate())) {

            user.getUsedCoupons().add(coupon);
            userRepository.save(user);

            double discountedPrice = (cart.getTotalSellingPrice() * coupon.getDiscountPercentage()) / 100;
            cart.setTotalSellingPrice(cart.getTotalSellingPrice()-discountedPrice);
            cartRepository.save(cart);
            return cart;

        }

        throw new CouponException("Coupon is not valid!");
    }

    public Cart removeCoupon(String code,User user) throws CouponException{

        Coupon coupon = couponRepository.findByCode(code);

        Cart cart = cartRepository.findByUserId(user.getId());

        if (coupon==null) {

            throw new CouponException("Coupon has not been find");

        }

        double discountedPrice = (cart.getTotalSellingPrice() * coupon.getDiscountPercentage()) / 100;

        cart.setTotalSellingPrice(cart.getTotalSellingPrice()+discountedPrice);
        cart.setCouponCode(null);

        return cartRepository.save(cart);

    }

    public Coupon findCouponById(Long id) throws CouponException{
        return couponRepository.findById(id).orElseThrow(() -> new CouponException("Coupon has not been find with id -> "+id.toString()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Coupon createCoupon(Coupon coupon){

        return couponRepository.save(coupon);

    }

    public List<Coupon> findAllCoupon(){
        return couponRepository.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCoupon(Long id) throws CouponException{
        Coupon coupon = this.findCouponById(id);
        couponRepository.delete(coupon);
    }






















}

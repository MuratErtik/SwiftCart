package com.project.ecommerce.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.config.JwtProvider;
import com.project.ecommerce.entities.Product;
import com.project.ecommerce.entities.User;
import com.project.ecommerce.entities.WishList;
import com.project.ecommerce.services.ProductService;
import com.project.ecommerce.services.UserService;
import com.project.ecommerce.services.WishlistService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    private final UserService userService;

    private final JwtProvider jwtProvider;

    private final ProductService productService;


    @GetMapping
    public ResponseEntity<WishList> getWishListByUserId(@RequestHeader("Authorization") String jwt) throws Exception{

        String email = jwtProvider.getEmailFromJwtToken(jwt);

        User user = userService.findUserByEmail(email);

        WishList wishList = wishlistService.getWishListByUserId(user);

        return new ResponseEntity<>(wishList,HttpStatus.OK);
        
    }

    @PostMapping("/add-product/{productId}")
    public ResponseEntity<WishList> addProductToWishlist(@RequestHeader("Authorization") String jwt, @PathVariable Long productId) throws Exception{

        String email = jwtProvider.getEmailFromJwtToken(jwt);

        User user = userService.findUserByEmail(email);

        Product product = productService.findProductById(productId);

        WishList wishListToUpdate = wishlistService.addProductToWishlist(user, product);

        return new ResponseEntity<>(wishListToUpdate,HttpStatus.OK);
    }
}

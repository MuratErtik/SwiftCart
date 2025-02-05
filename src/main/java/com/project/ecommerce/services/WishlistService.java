package com.project.ecommerce.services;

import org.springframework.stereotype.Service;

import com.project.ecommerce.entities.Product;
import com.project.ecommerce.entities.User;
import com.project.ecommerce.entities.WishList;
import com.project.ecommerce.exceptions.WishlistException;
import com.project.ecommerce.repository.WishlistRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WishlistService {

    private final WishlistRepository wishlistRepository;

    public WishList createWishlist(User user) {

        WishList wishList = new WishList();
        wishList.setUser(user);
        return wishlistRepository.save(wishList);
    }

    public WishList getWishListByUserId(User user) throws WishlistException {
        WishList wishList = wishlistRepository.findByUserId(user.getId());

        if (wishList == null) {
            wishList = this.createWishlist(user);
        }
        return wishList;
    }

    public WishList addProductToWishlist(User user, Product product) throws WishlistException {

        WishList wishList = this.getWishListByUserId(user);

        if (wishList.getProducts().contains(product)) {
            wishList.getProducts().remove(product);
        }
        wishList.getProducts().add(product);

        return wishList;

    }

}

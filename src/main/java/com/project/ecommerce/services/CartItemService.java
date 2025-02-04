package com.project.ecommerce.services;


import org.springframework.stereotype.Service;

import com.project.ecommerce.entities.CartItem;
import com.project.ecommerce.entities.User;
import com.project.ecommerce.exceptions.CartException;
import com.project.ecommerce.repository.CartItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartItemService {

    private final CartItemRepository cartItemRepository;

    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartException{


        CartItem cartItemToUpdate = this.findCartItem(id);

        User cartItemUser = cartItemToUpdate.getCart().getUser();

        if (cartItemUser.getId().equals(userId)) {
            cartItemToUpdate.setQuantity(cartItem.getQuantity());
            cartItemToUpdate.setMrpPrice(cartItemToUpdate.getQuantity()*cartItemToUpdate.getProduct().getMrpPrice());
            cartItemToUpdate.setSellingPrice(cartItemToUpdate.getQuantity()*cartItemToUpdate.getProduct().getSellingPrice());
            return cartItemRepository.save(cartItemToUpdate);
        }
        throw new CartException("You can not update this Cart Item!");

    }

    public void removeCardItem(Long userId, Long id) throws CartException{

        CartItem cartItemToDelete = this.findCartItem(id);

        User cartItemUser = cartItemToDelete.getCart().getUser();

        if (cartItemUser.getId().equals(userId)) {
            cartItemRepository.delete(cartItemToDelete);
        }
        else{
            throw new CartException("You can not delete this Cart Item");
        }

    }

    public CartItem findCartItem(Long id) throws CartException{
        return cartItemRepository.findById(id).orElseThrow(() -> new CartException("Item could not find wiht id -> "+id));
    }
}

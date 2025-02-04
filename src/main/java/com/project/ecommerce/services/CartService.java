package com.project.ecommerce.services;

import org.springframework.stereotype.Service;

import com.project.ecommerce.entities.Cart;
import com.project.ecommerce.entities.CartItem;
import com.project.ecommerce.entities.Product;
import com.project.ecommerce.entities.User;
import com.project.ecommerce.repository.CartItemRepository;
import com.project.ecommerce.repository.CartRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    private final CartItemRepository cartItemRepository;

    public CartItem addCartItem(User user,Product product,String size,int quantity) throws IllegalAccessException{

       Cart cart = findUserCart(user);

       CartItem isPresent = cartItemRepository.findByCartAndProductAndSize(cart, product, size); 

       if (isPresent == null) {

            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity); 
            cartItem.setUserId(user.getId());
            cartItem.setSize(size);

            int totalPrice = quantity * product.getSellingPrice();
            cartItem.setSellingPrice(totalPrice);

            cartItem.setMrpPrice(quantity * product.getMrpPrice());

            cart.getCartItems().add(cartItem); 
            cartItem.setCart(cart);

            return cartItemRepository.save(cartItem);
       }
       return isPresent;

    }

    public Cart findUserCart(User user) throws IllegalAccessException{
        Cart cart = cartRepository.findByUserId(user.getId());

        int totalPrice=0; 
        int totalDiscountedPrice=0; 
        int totalItem=0;

        for( CartItem cartItem: cart.getCartItems()){
            totalPrice +=  cartItem.getMrpPrice();
            totalDiscountedPrice += cartItem.getSellingPrice();
            totalItem += cartItem.getQuantity();
        }

        cart.setTotalMrpPrice(totalPrice);
        cart.setTotalItem(totalItem);
        cart.setTotalSellingPrice(totalDiscountedPrice);
        cart.setDiscount(calculateDiscountPercentage(totalPrice, totalItem));
        cart.setTotalItem(totalItem);

        return cart;
    }
    private int calculateDiscountPercentage(double mrp, double sellingPrice) throws IllegalAccessException {

        if (mrp <= 0) {
            return 0;
        }

        double discount = mrp - sellingPrice;
        double discountPercentage = (discount / mrp) * 100;
        return (int) discountPercentage;

    }
}

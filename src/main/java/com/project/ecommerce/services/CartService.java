package com.project.ecommerce.services;

import org.springframework.stereotype.Service;

import com.project.ecommerce.repository.CartItemRepository;
import com.project.ecommerce.repository.CartRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    private final CartItemRepository cartItemRepository;

    
}

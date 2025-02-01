package com.project.ecommerce.services;

import org.springframework.stereotype.Service;

import com.project.ecommerce.config.JwtProvider;
import com.project.ecommerce.entities.User;
import com.project.ecommerce.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public User findUserByJwt(String jwt) throws Exception {

        String email = jwtProvider.getEmailFromJwtToken(jwt);

        return this.findUserByEmail(email);

    }

    public User findUserByEmail(String email) throws Exception {

        User user = userRepository.findByEmail(email);

        if (user==null) {
            throw new Exception("User could not find with email -> "+email);
        }
        
        return user;
    }

}

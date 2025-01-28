package com.project.ecommerce.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.entities.User;
import com.project.ecommerce.repository.UserRepository;
import com.project.ecommerce.responses.SignupRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;

    @PostMapping("signup")
    public ResponseEntity<User> createUserHandler(@RequestBody SignupRequest signupRequest){

        User user = new User();
        
        user.setEmail(signupRequest.getEmail());

        user.setFullName(signupRequest.getFullName());

        User userToSave= userRepository.save(user);

        return ResponseEntity.ok(userToSave);

    }
}

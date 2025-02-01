package com.project.ecommerce.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.entities.User;
import com.project.ecommerce.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users/profile")
    public ResponseEntity<User> createUserHandler(@RequestHeader("Authorization") String jwt) throws Exception{
        
        User user  = userService.findUserByJwt(jwt);

        return ResponseEntity.ok(user); 
    }


    

}

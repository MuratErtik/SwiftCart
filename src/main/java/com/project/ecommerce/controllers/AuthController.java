package com.project.ecommerce.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.domain.USER_ROLE;
import com.project.ecommerce.repository.UserRepository;
import com.project.ecommerce.responses.AuthResponse;
import com.project.ecommerce.responses.SignupRequest;
import com.project.ecommerce.services.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final AuthService authService;

    @PostMapping("signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody SignupRequest signupRequest) {

        String jwt = authService.createUser(signupRequest);
        AuthResponse authResponse = new AuthResponse();

        authResponse.setJwt(jwt);

        authResponse.setMessage("Registered Successfully");

        authResponse.setRole(USER_ROLE.ROLE_CUSTOMER);

        return ResponseEntity.ok(authResponse);

    }
}

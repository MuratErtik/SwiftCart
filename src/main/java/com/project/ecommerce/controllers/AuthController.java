package com.project.ecommerce.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.domain.USER_ROLE;
import com.project.ecommerce.entities.Verification;
import com.project.ecommerce.responses.ApiResponse;
import com.project.ecommerce.responses.AuthResponse;
import com.project.ecommerce.responses.SignupRequest;
import com.project.ecommerce.services.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody SignupRequest signupRequest) throws Exception {

        String jwt = authService.createUser(signupRequest);
        AuthResponse authResponse = new AuthResponse();

        authResponse.setJwt(jwt);

        authResponse.setMessage("Registered Successfully");

        authResponse.setRole(USER_ROLE.ROLE_CUSTOMER);

        return ResponseEntity.ok(authResponse);

    }

    @PostMapping("/sent/login-signup-otp")
    public ResponseEntity<ApiResponse> sentOtpHandler(@RequestBody Verification verification) throws Exception {

        authService.sentLoginOtp(verification.getEmail());
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setMessage("Opt sent successfully!");

        return ResponseEntity.ok(apiResponse);

    }
}

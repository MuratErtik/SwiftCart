package com.project.ecommerce.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.domain.AccountStatus;
import com.project.ecommerce.entities.Seller;
import com.project.ecommerce.entities.Verification;
 import com.project.ecommerce.repository.VerificationRepository;
import com.project.ecommerce.requests.LoginRequest;
import com.project.ecommerce.responses.AuthResponse;
import com.project.ecommerce.services.AuthService;
import com.project.ecommerce.services.EmailService;
import com.project.ecommerce.services.SellerService;
import com.project.ecommerce.utils.OtpUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sellers")
public class SellerController {

    private final SellerService sellerService;
    private final VerificationRepository verificationRepository;
    private final AuthService authService;

    private final EmailService emailService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginSeller(@RequestBody LoginRequest loginRequest) throws Exception {

        String otp = loginRequest.getOtp();
        String email = loginRequest.getEmail();

        loginRequest.setEmail("seller_" + email);

        AuthResponse authResponse = authService.signing(loginRequest);

        return ResponseEntity.ok(authResponse);

    }

    @PatchMapping("/verify/{otp}")
    public ResponseEntity<Seller> verifySellerEmail(@PathVariable String otp) throws Exception {

        Verification verification = verificationRepository.findByOtp(otp);

        if (verification == null || !verification.getOtp().equals(otp)) {

            throw new Exception("Wrong OTP!");
        }

        Seller seller = sellerService.verifyMail(verification.getEmail(), otp);

        return new ResponseEntity<>(seller, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Seller> createSeller(@RequestBody Seller seller) throws Exception {
        Seller sellerToSave = sellerService.createSeller(seller);

        String otp = OtpUtil.generateOtp();
        Verification verification = new Verification();
        verification.setOtp(otp);
        verification.setEmail(seller.getEmail());
        verificationRepository.save(verification);

        String subject = "SwiftCart Email Verification Code";
        String text = "Welcome to SwiftCart, verify your account using this Link ->  ";
        String frontend_url = "http://localhost:3000/verify-seller/";
        emailService.sendVerificationOtpMail(seller.getEmail(), verification.getOtp(), subject, text + frontend_url);
        return new ResponseEntity<>(sellerToSave, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Seller> getSellerById(@PathVariable Long id) throws Exception{

        Seller seller = sellerService.getSellerById(id);

        return new ResponseEntity<>(seller,HttpStatus.OK);
    }

    // @GetMapping("/profile")
    // public ResponseEntity<Seller> getSellerByJwt(@RequestHeader String jwt) throws Exception{


    //     Seller seller = sellerService.getSellerByProfile(jwt);

    //     return new ResponseEntity<>(seller,HttpStatus.OK);
    // }

    @GetMapping
    public ResponseEntity<List<Seller>> getAllSellers(@RequestParam(required = false) AccountStatus status){
        List<Seller> allSellers = sellerService.getAllSellers(status);
        return ResponseEntity.ok(allSellers);
    }

    @PatchMapping
    public ResponseEntity<Seller> updateSeller(@RequestHeader("Authorization") String jwt, @RequestBody Seller seller) throws Exception{
        Seller profile = sellerService.getSellerByProfile(jwt);
        Seller sellerToUpdate = sellerService.updateSeller(profile.getId(), seller);
        return ResponseEntity.ok(sellerToUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeller(@PathVariable Long id) throws Exception{

        sellerService.deleteSeller(id);

        return ResponseEntity.noContent().build();
    }

}

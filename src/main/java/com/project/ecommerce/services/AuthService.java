package com.project.ecommerce.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.ecommerce.config.JwtProvider;
import com.project.ecommerce.domain.USER_ROLE;
import com.project.ecommerce.entities.Cart;
import com.project.ecommerce.entities.Seller;
import com.project.ecommerce.entities.User;
import com.project.ecommerce.entities.Verification;
import com.project.ecommerce.exceptions.SellerException;
import com.project.ecommerce.repository.CartRepository;
import com.project.ecommerce.repository.SellerRepository;
import com.project.ecommerce.repository.UserRepository;
import com.project.ecommerce.repository.VerificationRepository;
import com.project.ecommerce.requests.LoginRequest;
import com.project.ecommerce.responses.AuthResponse;
import com.project.ecommerce.responses.SignupRequest;
import com.project.ecommerce.utils.OtpUtil;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CartRepository cartRepository;
    private final JwtProvider jwtProvider;
    private final VerificationRepository verificationRepository;
    private final EmailService emailService;
    private final CustomUserService customUserService;
    private final SellerRepository sellerRepository;

    public void sentLoginOtp(String email, USER_ROLE role) throws SellerException,MessagingException {
        String SIGNING_PREFIX = "signin_";

        if (email.startsWith(SIGNING_PREFIX)) {
            email = email.substring(SIGNING_PREFIX.length());

            if (role == USER_ROLE.ROLE_SELLER) {
                Seller seller = sellerRepository.findByEmail(email);
                System.out.println(email);
                if (seller == null) {
                    throw new SellerException("seller not exist  with provided email!");
                }
            } else {

                User user = userRepository.findByEmail(email);

                if (user == null) {
                    throw new SellerException("user not exist  with provided email!");
                }
            }

        }
        Verification isExist = verificationRepository.findByEmail(email);

        if (isExist != null) {

            verificationRepository.delete(isExist);

        }
        String otp = OtpUtil.generateOtp();

        Verification verification = new Verification();
        verification.setOtp(otp);
        verification.setEmail(email);
        verificationRepository.save(verification);

        String subject = "SwiftCart Login/Signup One Time Password ";

        String text = "Your One Time Password is - " + otp + "\n Have a good day. Enjoy it:)";

        emailService.sendVerificationOtpMail(email, otp, subject, text);

    }

    public String createUser(SignupRequest request) throws SellerException {

        Verification verification = verificationRepository.findByEmail(request.getEmail());

        if (verification == null || !verification.getOtp().equals(request.getOtp())) {
            throw new SellerException("Wrong otp!");
        }

        User user = userRepository.findByEmail(request.getEmail());

        if (user == null) {
            User newUser = new User();

            newUser.setEmail(request.getEmail());

            newUser.setFullName(request.getFullName());

            newUser.setRole(USER_ROLE.ROLE_CUSTOMER);

            newUser.setMobile("5827328392");

            newUser.setPassword(passwordEncoder.encode(request.getOtp()));

            user = userRepository.save(newUser);

            Cart cart = new Cart();
            cart.setUser(user);
            cartRepository.save(cart);

        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(USER_ROLE.ROLE_CUSTOMER.toString())); // user.getRole().toString())
        Authentication authentication = new UsernamePasswordAuthenticationToken(request.getEmail(), null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtProvider.generateToken(authentication);
    }

    public AuthResponse signing(LoginRequest req) throws SellerException {
        String username = req.getEmail();

        String otp = req.getOtp();

        Authentication authentication = authenticate(username, otp);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();

        authResponse.setJwt(token);

        authResponse.setMessage("Login Successfully!");

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        String roleName = authorities.isEmpty() ? null : authorities.iterator().next().getAuthority();

        authResponse.setRole(USER_ROLE.valueOf(roleName));

        return authResponse;
    }

    private Authentication authenticate(String username, String otp) throws SellerException {

        UserDetails userDetails = customUserService.loadUserByUsername(username);

        String SELLER_PREFIX = "seller_" ;
        if (username.startsWith(SELLER_PREFIX)) {
            username=username.substring(SELLER_PREFIX.length());
            
        }

        if (userDetails == null) {
            throw new BadCredentialsException("Invalid mail or password!");
        }
        Verification verification = verificationRepository.findByEmail(username);

        if (verification == null || !verification.getOtp().equals(otp)) {
            throw new SellerException("Wrong OTP");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

    }
}

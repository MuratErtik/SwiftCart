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
import com.project.ecommerce.entities.User;
import com.project.ecommerce.entities.Verification;
import com.project.ecommerce.repository.CartRepository;
import com.project.ecommerce.repository.UserRepository;
import com.project.ecommerce.repository.VerificationRepository;
import com.project.ecommerce.requests.LoginRequest;
import com.project.ecommerce.responses.AuthResponse;
import com.project.ecommerce.responses.SignupRequest;
import com.project.ecommerce.utils.OtpUtil;

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

    public void sentLoginOtp(String email) throws Exception {
        String SIGNING_PREFIX = "signin_";

        if (email.startsWith(SIGNING_PREFIX)) {
            email = email.substring(SIGNING_PREFIX.length());

            User user = userRepository.findByEmail(email);

            if (user == null) {
                throw new Exception("user not exist  with provided email!");
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

        String subject = "SwiftCart Login/Signup One Time Password "; // it ll be change!

        String text = "Your One Time Password is - " + otp+"\n Have a good day. Enjoy it:)";

        emailService.sendVerificationOtpMail(email, otp, subject, text);

    }

    public String createUser(SignupRequest request) throws Exception {

        Verification verification = verificationRepository.findByEmail(request.getEmail());

        if (verification == null || !verification.getOtp().equals(request.getOtp())) {
            throw new Exception("Wrong otp!");
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

    public AuthResponse signing(LoginRequest req){
        String username = req.getEmail();

        String otp = req.getOtp();

        Authentication authentication = authenticate(username, otp);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();

        authResponse.setJwt(token);

        authResponse.setMessage("Login Successfully!");

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        String roleName = authorities.isEmpty()?null:authorities.iterator().next().getAuthority();

        authResponse.setRole(USER_ROLE.valueOf(roleName));


        return authResponse;
    }

    private Authentication authenticate(String username,String otp){

        UserDetails userDetails=customUserService.loadUserByUsername(username);

        if (userDetails==null) {
            throw new BadCredentialsException("Invalid mail or password!");
        }
        Verification verification = verificationRepository.findByEmail(username);

        if (verification==null || ! verification.getOtp().equals(otp)) {
            throw new BadCredentialsException("Wrong OTP");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
         

    }
}

package com.project.ecommerce.services;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.ecommerce.config.JwtProvider;
import com.project.ecommerce.domain.AccountStatus;
import com.project.ecommerce.domain.USER_ROLE;
import com.project.ecommerce.entities.Address;
import com.project.ecommerce.entities.Seller;
import com.project.ecommerce.exceptions.SellerException;
import com.project.ecommerce.repository.AddressRepository;
import com.project.ecommerce.repository.SellerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SellerService {

    private final SellerRepository sellerRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final AddressRepository addressRepository;

    public Seller getSellerRepository(String jwt) throws SellerException {

        String email = jwtProvider.getEmailFromJwtToken(jwt);

        return this.getSellerByEmail(email);

    }

    public Seller getSellerByEmail(String email) throws SellerException {

        Seller seller = sellerRepository.findByEmail(email);

        if (seller == null) {
            throw new SellerException("Seller could not find with email -> " + email);
        }

        return seller;

    }

    public Seller createSeller(Seller seller) throws SellerException {

        Seller existSeller = sellerRepository.findByEmail(seller.getEmail());

        if (existSeller != null) {
            throw new SellerException("Seller already exist please provide different email!");
        }
        Address addressToSave = addressRepository.save(seller.getPickupAddress());

        Seller newSeller = new Seller();

        newSeller.setEmail(seller.getEmail());

        newSeller.setPassword(passwordEncoder.encode(seller.getPassword()));

        newSeller.setSellerName(seller.getSellerName());

        newSeller.setPickupAddress(addressToSave);

        newSeller.setRole(USER_ROLE.ROLE_SELLER);

        newSeller.setMobile(seller.getMobile());

        newSeller.setBankDetails(seller.getBankDetails());

        newSeller.setBusinessDetails(seller.getBusinessDetails());

        return sellerRepository.save(newSeller);

    }

    public Seller getSellerById(Long id) throws SellerException {
        return sellerRepository.findById(id)
                .orElseThrow(() -> new SellerException("Seller could not find with id -> " + id.toString()));

    }

    public List<Seller> getAllSellers(AccountStatus status) {

        return sellerRepository.findByAccountStatus(status);

    }

    public Seller updateSeller(Long id, Seller seller) throws SellerException {
        Seller existSeller = this.getSellerById(id);

        if (seller.getSellerName() != null) {
            existSeller.setSellerName(seller.getSellerName());

        }
        if (seller.getMobile() != null) {
            existSeller.setMobile(seller.getMobile());

        }
        if (seller.getEmail() != null) {
            existSeller.setEmail(seller.getEmail());

        }
        if (seller.getBusinessDetails() != null && seller.getBusinessDetails().getBusinessName() != null) {
            existSeller.getBusinessDetails().setBusinessName(seller.getBusinessDetails().getBusinessName());
        }

        if (seller.getBankDetails() != null
                && seller.getBankDetails().getAccountHolderName() != null
                && seller.getBankDetails().getAccountNumber() != null
                && seller.getBankDetails().getIbanCode() != null) {

            existSeller.getBankDetails().setAccountHolderName(seller.getBankDetails().getAccountHolderName());

            existSeller.getBankDetails().setAccountNumber(seller.getBankDetails().getAccountNumber());

            existSeller.getBankDetails().setIbanCode(seller.getBankDetails().getIbanCode());

        }

        if (seller.getPickupAddress() != null
                && seller.getPickupAddress().getAddress() != null
                && seller.getPickupAddress().getCity() != null
                && seller.getPickupAddress().getDistrict() != null
                && seller.getPickupAddress().getLocality() != null) {

            existSeller.getPickupAddress().setAddress(seller.getPickupAddress().getAddress());
            existSeller.getPickupAddress().setCity(seller.getPickupAddress().getCity());
            existSeller.getPickupAddress().setDistrict(seller.getPickupAddress().getDistrict());
            existSeller.getPickupAddress().setLocality(seller.getPickupAddress().getLocality());

        }

        return sellerRepository.save(existSeller);
    }

    public void deleteSeller(long id) throws SellerException {
        Seller seller = sellerRepository.findById(id)
                .orElseThrow(() -> new SellerException("Seller could not find with id -> " + id));

        sellerRepository.delete(seller);
    }

    public Seller verifyMail(String email, String otp) throws SellerException {

        Seller seller = getSellerByEmail(email);
        seller.setEmailVerified(true);
        return sellerRepository.save(seller);

    }

    public Seller updateSellerAccountStatus(Long sellerId, AccountStatus status ) throws SellerException{

        Seller seller = this.getSellerById(sellerId);
        seller.setAccountStatus(status);
        return sellerRepository.save(seller);
    }

    public Seller getSellerByProfile(String jwt) throws SellerException {
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        return this.getSellerByEmail(email);
        
    }

}

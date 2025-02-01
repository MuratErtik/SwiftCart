package com.project.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ecommerce.entities.Address;

public interface AddressRepository extends JpaRepository<Address,Long> {

}

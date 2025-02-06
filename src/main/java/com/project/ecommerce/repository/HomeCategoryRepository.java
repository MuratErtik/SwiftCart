package com.project.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ecommerce.entities.HomeCategory;

public interface HomeCategoryRepository extends JpaRepository<HomeCategory,Long>{

    

}

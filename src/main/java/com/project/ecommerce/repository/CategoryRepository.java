package com.project.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ecommerce.entities.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    Category findByCategoryId(String categoryId);

}

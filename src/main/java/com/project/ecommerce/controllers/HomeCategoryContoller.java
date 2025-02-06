package com.project.ecommerce.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.entities.Home;
import com.project.ecommerce.entities.HomeCategory;
import com.project.ecommerce.exceptions.HomeCategoryException;
import com.project.ecommerce.services.HomeCategoryService;
import com.project.ecommerce.services.HomeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class HomeCategoryContoller {

    private final HomeCategoryService homeCategoryService;
    private final HomeService homeService;

    @PostMapping("/home/categories")
    public ResponseEntity<Home> createHomeCategories(@RequestBody List<HomeCategory> homeCategories) {

        List<HomeCategory> categories = homeCategoryService.createCategories(homeCategories);
        Home home = homeService.createHomePageData(categories);
        return new ResponseEntity<>(home, HttpStatus.ACCEPTED);

    }

    @GetMapping("/admin/home-category")
    public ResponseEntity<List<HomeCategory>> getHomeCategory() {
        List<HomeCategory> homeCategories = homeCategoryService.getAllHomeCategories();
        return new ResponseEntity<>(homeCategories, HttpStatus.OK);

    }

    @PatchMapping("/admin/home-category/{id}")
    public ResponseEntity<HomeCategory> updateHomeCategory(@PathVariable Long id,
            @RequestBody HomeCategory homeCategory) throws HomeCategoryException {

        HomeCategory homeCategoryToUpdate = homeCategoryService.updateHomeCategory(homeCategory, id);

        return new ResponseEntity<>(homeCategoryToUpdate, HttpStatus.ACCEPTED);
    }

}

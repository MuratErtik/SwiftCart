package com.project.ecommerce.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.ecommerce.entities.HomeCategory;
import com.project.ecommerce.exceptions.HomeCategoryException;
import com.project.ecommerce.repository.HomeCategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HomeCategoryService {

    private final HomeCategoryRepository homeCategoryRepository;

    public HomeCategory createHomeCategory(HomeCategory homeCategory){

        return homeCategoryRepository.save(homeCategory);
    }

    public List<HomeCategory> createCategories(List<HomeCategory> homeCategories){

        if (homeCategoryRepository.findAll().isEmpty()) {
            return homeCategoryRepository.saveAll(homeCategories);
        }

        return this.getAllHomeCategories();

    }

    public HomeCategory updateHomeCategory(HomeCategory homeCategory,Long id) throws HomeCategoryException{
        HomeCategory homeCategoryToupdate = homeCategoryRepository.findById(id).orElseThrow(() -> new HomeCategoryException("not found!!!"));

        if (homeCategory.getImage()!=null) {

            homeCategoryToupdate.setImage(homeCategory.getImage());
        }


        if (homeCategory.getCategoryId()!=null) {
            homeCategoryToupdate.setCategoryId(homeCategory.getCategoryId());
        }

        return homeCategoryRepository.save(homeCategoryToupdate);
    }

    public List<HomeCategory> getAllHomeCategories(){
        return homeCategoryRepository.findAll();
    }
}

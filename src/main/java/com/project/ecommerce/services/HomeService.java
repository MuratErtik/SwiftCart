package com.project.ecommerce.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.ecommerce.domain.HomeCategorySection;
import com.project.ecommerce.entities.Deal;
import com.project.ecommerce.entities.Home;
import com.project.ecommerce.entities.HomeCategory;
import com.project.ecommerce.repository.DealRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HomeService {

    private final DealRepository dealRepository;

    public Home createHomePageData(List<HomeCategory> allCategories) {

        /*
         * Bu metot, Home adlı bir ana sayfa nesnesi oluşturur ve onu farklı kategori
         * türlerine göre doldurur. Eğer daha önce oluşturulmamışsa, "Deal" (Fırsat)
         * nesnelerini de veritabanına ekler.
         * 
         * 
         * 
         */
        List<HomeCategory> gridCategories = allCategories.stream()
                .filter(category -> category.getSection() == HomeCategorySection.GRID).collect(Collectors.toList());

        List<HomeCategory> shopByCategories = allCategories.stream()
                .filter(category -> category.getSection() == HomeCategorySection.SHOP_BY_CATEGORIES)
                .collect(Collectors.toList());

        List<HomeCategory> electricCategories = allCategories.stream()
                .filter(category -> category.getSection() == HomeCategorySection.ELECTRIC_CATEGORIES)
                .collect(Collectors.toList());

        List<HomeCategory> dealCategories = allCategories.stream()
                .filter(category -> category.getSection() == HomeCategorySection.DEALS).collect(Collectors.toList());

        List<Deal> createdDeals = new ArrayList<>();

        if (dealRepository.findAll().isEmpty()) {
            List<Deal> deals = allCategories.stream()
                    .filter(category -> category.getSection() == HomeCategorySection.DEALS)
                    .map(category -> new Deal(null, 10, category)).collect(Collectors.toList());
            createdDeals = dealRepository.saveAll(deals);
        } else {
            createdDeals = dealRepository.findAll();
        }

        Home home = new Home();
        home.setGrid(gridCategories);
        home.setShopByCategories(shopByCategories);
        home.setElectricCategories(electricCategories);
        home.setDealCategories(dealCategories);

        return home;
    }
}

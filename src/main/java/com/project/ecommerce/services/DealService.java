package com.project.ecommerce.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.ecommerce.entities.Deal;
import com.project.ecommerce.entities.HomeCategory;
import com.project.ecommerce.exceptions.HomeCategoryException;
import com.project.ecommerce.repository.DealRepository;
import com.project.ecommerce.repository.HomeCategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DealService {

    private final DealRepository dealRepository;

    private final HomeCategoryRepository homeCategoryRepository;

    public List<Deal> getAllDeals(){
        return dealRepository.findAll();
    }

    public Deal createDeal(Deal deal) throws HomeCategoryException{

        HomeCategory homeCategory = homeCategoryRepository.findById(deal.getCategory().getId()).orElseThrow(() -> new HomeCategoryException("not found!!!"));
        Deal newDeal =  dealRepository.save(deal);
        newDeal.setCategory(homeCategory);
        newDeal.setDiscount(deal.getDiscount());
        return dealRepository.save(newDeal);
    }

    public void deleteDeal(Long id) throws HomeCategoryException{

        Deal deal = dealRepository.findById(id).orElseThrow(() -> new HomeCategoryException("not found!!!"));
        dealRepository.delete(deal);
    }

    public Deal updateDeal(Deal deal,Long dealId) throws HomeCategoryException{
        Deal dealToUpdate = dealRepository.findById(dealId).orElse(null);
        HomeCategory category = homeCategoryRepository.findById(deal.getCategory().getId()).orElse(null);

        if (dealToUpdate!=null) {
            if (deal.getDiscount()!=null) {
                dealToUpdate.setDiscount(deal.getDiscount());
            }
            if (category!=null) {
                dealToUpdate.setCategory(category);
            }

            return dealRepository.save(dealToUpdate);

        }

        throw new HomeCategoryException("Deal not found..,");


    }
}

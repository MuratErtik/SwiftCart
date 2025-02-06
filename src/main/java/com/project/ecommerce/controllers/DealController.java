package com.project.ecommerce.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.entities.Deal;
import com.project.ecommerce.exceptions.HomeCategoryException;
import com.project.ecommerce.responses.ApiResponse;
import com.project.ecommerce.services.DealService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/deals")
public class DealController {

    private final DealService dealService;

    @PostMapping
    public ResponseEntity<Deal> createDeal(@RequestBody Deal deal) throws HomeCategoryException{

        Deal dealToCreate = dealService.createDeal(deal);
        return new ResponseEntity<>(dealToCreate,HttpStatus.ACCEPTED);

    }

    @PatchMapping("/id")
    public ResponseEntity<Deal> updateDeal(@PathVariable Long id,@RequestBody Deal deal) throws HomeCategoryException{
        
        Deal  dealToUpdate = dealService.updateDeal(deal, id);

        return new ResponseEntity<>(dealToUpdate,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/id")
    public ResponseEntity<ApiResponse> deleteDeal(@PathVariable Long id) throws HomeCategoryException{

        dealService.deleteDeal(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("deal deleted successfully!");
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);

    }













}

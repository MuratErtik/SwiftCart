package com.project.ecommerce.services;

import org.springframework.stereotype.Service;

import com.project.ecommerce.entities.Seller;
import com.project.ecommerce.entities.SellerReport;
import com.project.ecommerce.repository.SellerReportRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SellerReportService {

    private final SellerReportRepository sellerReportRepository;

    public SellerReport getSellerReport (Seller seller){
        SellerReport sr = sellerReportRepository.findBySellerId(seller.getId());

        if (sr==null) {
            SellerReport sr2 = new SellerReport();
            sr2.setSeller(seller);
            return sellerReportRepository.save(sr2);
        }
        return sr;
    }

    public SellerReport updateSellerReport(SellerReport sellerReport){
        return sellerReportRepository.save(sellerReport);
    }
}

package com.project.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ecommerce.entities.SellerReport;

public interface SellerReportRepository extends JpaRepository<SellerReport,Long>{


    SellerReport findBySellerId(Long sellerId);

    SellerReport updateSellerReport(SellerReport sellerReport);

}

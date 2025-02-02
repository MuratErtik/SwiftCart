package com.project.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.project.ecommerce.entities.Product;

public interface ProductRepository extends JpaRepository<Product,Long> , JpaSpecificationExecutor<Product> {
    /*
     *  Filtreleme (kategori, renk, beden, fiyat aralığı gibi dinamik parametreler)
        Arama kriterlerine göre özelleştirilmiş SQL sorguları oluşturma
        Büyük veri kümelerinde esnek ve optimize sorgular yapma
     */
    List<Product> findBySellerId(Long sellerId);
    

}

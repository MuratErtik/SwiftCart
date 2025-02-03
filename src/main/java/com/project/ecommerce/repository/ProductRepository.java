package com.project.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.ecommerce.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    /*
     * Filtreleme (kategori, renk, beden, fiyat aralığı gibi dinamik parametreler)
     * Arama kriterlerine göre özelleştirilmiş SQL sorguları oluşturma
     * Büyük veri kümelerinde esnek ve optimize sorgular yapma
     */
    List<Product> findBySellerId(Long sellerId);

    // JPQL (Java Persistence Query Language)
    // Buradaki p, Product entity'sini temsil eder.
    // Kullanıcının bir sorgu (query) girmediği durumda, tüm ürünleri getir.
    // lower(p.title) → Harf duyarlılığını kaldırır (BÜYÜK/küçük harf farkını
    // önler).
    // title içinde query geçiyorsa bu ürünü getir.

    
    @Query("SELECT p FROM Product p WHERE (:query IS NULL OR lower(p.title) LIKE lower(concat('%',:query,'%')) " +
       "OR lower(p.category.name) LIKE lower(concat('%',:query,'%')))")
    List<Product> searchProducts(@Param("query") String query);

    

}

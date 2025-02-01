package com.project.ecommerce.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.project.ecommerce.entities.Category;
import com.project.ecommerce.entities.Product;
import com.project.ecommerce.entities.Seller;
import com.project.ecommerce.exceptions.ProductException;
import com.project.ecommerce.repository.CategoryRepository;
import com.project.ecommerce.repository.ProductRepository;
import com.project.ecommerce.requests.CreateProductRequest;

import jakarta.persistence.criteria.Join;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public Product createProduct(CreateProductRequest request, Seller seller) throws IllegalAccessException {

        Category category1 = categoryRepository.findByCategoryId(request.getCategory());

        if (category1 == null) {
            Category category = new Category();
            category.setCategoryId(request.getCategory());
            category.setLevel(1);
            category1 = categoryRepository.save(category);

        }

        Category category2 = categoryRepository.findByCategoryId(request.getCategory2());

        if (category2 == null) {
            Category category = new Category();
            category.setCategoryId(request.getCategory2());
            category.setLevel(2);
            category.setParentCategory(category1);
            category2 = categoryRepository.save(category);

        }

        Category category3 = categoryRepository.findByCategoryId(request.getCategory3());

        if (category3 == null) {
            Category category = new Category();
            category.setCategoryId(request.getCategory3());
            category.setLevel(3);
            category.setParentCategory(category2);
            category3 = categoryRepository.save(category);

        }

        int discountPercentage = calculateDiscountPercentage(request.getMrpPrice(), request.getSellingPrice());

        Product product = new Product();

        product.setSeller(seller);
        product.setCategory(category3);
        product.setDescription(request.getDescription());
        product.setCreatedTime(LocalDateTime.now());
        product.setTitle(request.getTitle());
        product.setColor(request.getColor());
        product.setSellingPrice(request.getSellingPrice());
        product.setImages(request.getImages());
        product.setMrpPrice(request.getMrpPrice());
        product.setSize(request.getSizes());
        product.setDiscountPercent(discountPercentage);

        return productRepository.save(product);

    }

    private int calculateDiscountPercentage(double mrp, double sellingPrice) throws IllegalAccessException {

        if (mrp <= 0) {
            throw new IllegalAccessException("Price must be greater than 0");
        }

        double discount = mrp - sellingPrice;
        double discountPercentage = (discount / mrp) * 100;
        return (int) discountPercentage;

    }

    public void deleteProduct(Long productId) throws ProductException {
        Product product = this.findProductById(productId);
        productRepository.delete(product);
    }

    public Product updateProduct(Long productId, Product product) throws ProductException {

        findProductById(productId);
        product.setId(productId);

        return productRepository.save(product);

        // have a check after
    }

    public Product findProductById(Long productId) throws ProductException {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductException("The product has not been found with id -> " + productId));

    }

    public List<Product> searchProduct(){

        return List.of();
    }

    // public Page<Product> getAllProduct(String category,String brand,String colors,String sizes,Integer minPrice, 
    //                                     Integer maxPrice,Integer minDiscount,String sort,String stock,Integer pageNumber){
                                        
    //     Specification<Product> spec = (root,query,criteriaBuilder) -> {
    //         List<Predicate> predicates = new ArrayList<>();

    //         if (category==null) {
    //             Join<Product,Category> categoryJoin = root.join("category");
    //             predicates.add((Predicate) criteriaBuilder.equal(categoryJoin.get("categoryId"), category));
    //         }
    //     };
    // }
}

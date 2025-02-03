package com.project.ecommerce.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.entities.Product;
import com.project.ecommerce.exceptions.ProductException;
import com.project.ecommerce.services.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) throws ProductException {
        Product product = productService.findProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    // HTTP isteğinde sorgu parametrelerini almak için kullanılır.
    /*
     * Spring Boot, @RequestParam ile belirtilen bir parametreyi zorunlu (required =
     * true) olarak kabul eder.
     * Eğer bu parametreyi zorunlu yapmak istemiyorsak, required = false olarak
     * ayarlarız.
     * Böylece, parametre gönderilmezse hata alınmaz ve değeri null olur.
     */
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProduct(@RequestParam(required = false) String query) {
        List<Product> products = productService.searchProduct(query);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Product>> getAllProduct(
        @RequestParam(required = false) String category,
        @RequestParam(required = false) String brand,
        @RequestParam(required = false) String color,
        @RequestParam(required = false) String size,
        @RequestParam(required = false) Integer minPrice,
        @RequestParam(required = false) Integer maxPrice,
        @RequestParam(required = false) Integer minDiscount,
        @RequestParam(required = false) String sort,
        @RequestParam(required = false) String stock,
        @RequestParam(defaultValue = "0") Integer pageNumber){
            return new ResponseEntity<>(productService.getAllProduct(category, brand, color, size, minPrice, maxPrice, minDiscount, sort, stock, pageNumber),HttpStatus.OK);
        }
        

    

}

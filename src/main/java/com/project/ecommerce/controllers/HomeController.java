package com.project.ecommerce.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.responses.ApiResponse;

@RestController
/*
 * RestController, Spring Framework'te bir sınıfı RESTful bir web servisinin kontrolcüsü (controller) olarak tanımlamak için kullanılır. 
 * REST API'leri oluşturmak için gerekli olan bir anotasyondur ve web istemcilerine  JSON veya XML formatında veri döndürmek için tasarlanmıştır.
 */
public class HomeController {

    @GetMapping
    public ApiResponse HomeControllerHandler(){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Welcome to main page! 1");
        return  apiResponse;
    }


}

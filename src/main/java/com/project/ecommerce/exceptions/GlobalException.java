package com.project.ecommerce.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


//Spring Boot'ta hata yönetimi yapmak için kullanılır.
//Böylece, her Controller içinde ayrı ayrı hata yönetimi yazmak zorunda kalmayız.


@ControllerAdvice
public class GlobalException {

    // bir sellerexcep olursa bu method devreye girer.
    @ExceptionHandler(SellerException.class)
    public ResponseEntity<ErrorDetail> sellerExceptionHandler(SellerException se , WebRequest webRequest){

        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setError(se.getMessage());
        errorDetail.setDetails(webRequest.getDescription(false));
        errorDetail.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(errorDetail,HttpStatus.BAD_REQUEST);

    }
}












package com.project.ecommerce.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.config.JwtProvider;
import com.project.ecommerce.entities.Product;
import com.project.ecommerce.entities.Review;
import com.project.ecommerce.entities.User;
import com.project.ecommerce.requests.CreateReviewRequest;
import com.project.ecommerce.responses.ApiResponse;
import com.project.ecommerce.services.ProductService;
import com.project.ecommerce.services.ReviewService;
import com.project.ecommerce.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;
    private final UserService userService;
    private final ProductService productService;
    private final JwtProvider jwtProvider;

    @GetMapping("/products/{productId}/reviews")
    public ResponseEntity<List<Review>> getReviewsByProductId(@PathVariable Long productid){

        List<Review> reviews = reviewService.getReviewByProductId(productid);

        return new ResponseEntity<>(reviews,HttpStatus.OK);

    }

    @PostMapping("/products/{productId}/reviews")
    public ResponseEntity<Review> addReview(@RequestHeader("Authorization") String jwt, @RequestBody CreateReviewRequest request, @PathVariable Long productId) throws Exception{


        String email = jwtProvider.getEmailFromJwtToken(jwt);
        User user = userService.findUserByEmail(email);

        Product product = productService.findProductById(productId);

        Review review = reviewService.createReview(request, user,product );

        return new ResponseEntity<>(review,HttpStatus.CREATED);

    }

    @PatchMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> updateReview(@RequestHeader("Authorization") String jwt,@RequestBody CreateReviewRequest request, @PathVariable Long reviewId) throws Exception{

        String email = jwtProvider.getEmailFromJwtToken(jwt);
        User user = userService.findUserByEmail(email);

        Review review = reviewService.updateReview(reviewId, request.getReviewText(), request.getReviewRating(), user.getId());

        return new ResponseEntity<>(review,HttpStatus.OK);

        
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<ApiResponse> deleteReview(@RequestHeader("Authorization") String jwt,@PathVariable Long reviewId ) throws Exception{

        String email = jwtProvider.getEmailFromJwtToken(jwt);
        User user = userService.findUserByEmail(email);

        reviewService.deleteReview(reviewId, user.getId());

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("deleted successfully");
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);

    }
}

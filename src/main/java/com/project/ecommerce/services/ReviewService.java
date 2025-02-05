package com.project.ecommerce.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.ecommerce.entities.Product;
import com.project.ecommerce.entities.Review;
import com.project.ecommerce.entities.User;
import com.project.ecommerce.exceptions.ReviewException;
import com.project.ecommerce.repository.Reviewrepository;
import com.project.ecommerce.requests.CreateReviewRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final Reviewrepository reviewrepository;

    public Review createReview(CreateReviewRequest request,User user,Product product){

        Review review = new Review();
        review.setUser(user);
        review.setProduct(product);
        review.setReviewText(request.getReviewText());
        review.setRating(request.getReviewRating());
        review.setProductImages(request.getProductImages()) ;
        product.getReviews().add(review);

        return reviewrepository.save(review);

    }
    
    public List<Review> getReviewByProductId(Long productId) {
        return reviewrepository.findByProductId(productId);
        }

    public Review updateReview(Long reviewId, String reviewText, double rating, Long userId) throws ReviewException{
        Review review = this.getReviewById(reviewId);

        if (review.getUser().getId().equals(userId)) {
            review.setReviewText(reviewText);
            review.setRating(rating);
            return reviewrepository.save(review);
        }
        throw new ReviewException("you can not update this review!");

    } 

    public Review getReviewById(Long id) throws ReviewException{
        return reviewrepository.findById(id).orElseThrow(() -> new ReviewException("could not find it!"));
    }

    public void deleteReview(Long reviewId,Long userId) throws ReviewException{

        Review review = this.getReviewById(reviewId);

        if (!review.getUser().getId().equals(userId)) {
            throw new ReviewException("You can not delete this review");
        }
        reviewrepository.delete(review);

    }



}

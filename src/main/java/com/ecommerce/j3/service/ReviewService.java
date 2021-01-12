package com.ecommerce.j3.service;

import com.ecommerce.j3.domain.Review;
import com.ecommerce.j3.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewService {
    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository){this.reviewRepository = reviewRepository;}

    public Review save(Review review){
        reviewRepository.save(review);
        return review;
    }

    public Review update(Review review){
        reviewRepository.save(review);
        return review;
    }

    public Optional<Review> findOneById(Long reviewId){
        return reviewRepository.findById(reviewId);
    }
}

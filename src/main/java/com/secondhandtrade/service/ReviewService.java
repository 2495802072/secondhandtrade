package com.secondhandtrade.service;

import com.secondhandtrade.model.Product;
import com.secondhandtrade.model.Review;
import com.secondhandtrade.model.User;
import com.secondhandtrade.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public Review save(Review review) {
        review.setCreatedAt(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    public Review findById(long id) {
        return reviewRepository.findByReviewId(id);
    }

    @Transactional
    public void deleteById(long id) {
        reviewRepository.deleteById(id);
    }

    public List<Review> findByOwner(User owner){
        return reviewRepository.findByOwner(owner);
    }

    public List<Review> findByProduct(Product product){
        return reviewRepository.findByProduct(product);
    }

    public List<Review> findByRatingBetween(int low, int high){
        return reviewRepository.findByRatingBetween(low, high);
    }

    public List<Review> findByCreatedAfter(LocalDateTime dateTime){
        return  reviewRepository.findByCreatedAtAfter(dateTime);
    }
}

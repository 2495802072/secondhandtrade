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

    // 保存评论
    public Review save(Review review) {
        review.setCreatedAt(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    // 查询所有评论
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    // 通过id查找评论
    public Review findById(long id) {
        return reviewRepository.findByReviewId(id);
    }

    // 通过id删除评论
    @Transactional
    public void deleteById(long id) {
        reviewRepository.deleteById(id);
    }

    // 通过用户查找评论
    public List<Review> findByUser(User owner) {
        return reviewRepository.findByUser(owner);
    }

    // 通过商品查找评论
    public List<Review> findByProduct(Product product) {
        return reviewRepository.findByProduct(product);
    }

    // 通过评分范围查找评论
    public List<Review> findByRatingBetween(int low, int high) {
        return reviewRepository.findByRatingBetween(low, high);
    }

    // 通过创建时间查找评论
    public List<Review> findByCreatedAfter(LocalDateTime dateTime) {
        return reviewRepository.findByCreatedAtAfter(dateTime);
    }
}

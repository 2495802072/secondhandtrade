package com.secondhandtrade.repository;

import com.secondhandtrade.model.Product;
import com.secondhandtrade.model.Review;
import com.secondhandtrade.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Review findByReviewId(Long id);

    List<Review> findByProduct(Product p);

    List<Review> findByUser(User u);

    List<Review> findByRatingBetween(int min, int max);

    List<Review> findByCreatedAtAfter(LocalDateTime createdAt);
}

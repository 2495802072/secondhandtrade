package com.secondhandtrade.controller;

import com.secondhandtrade.model.Review;
import com.secondhandtrade.model.User;
import com.secondhandtrade.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public List<Review> getReviews() {
        return reviewService.findAll();
    }

    @PostMapping
    public Review addReview(@RequestBody Review review) {
        return reviewService.save(review);
    }

    @GetMapping("/{id}")
    public Review getReview(@PathVariable long id) {
        return reviewService.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable long id) {
        Review review = reviewService.findById(id);
        if (review == null) {
            return new ResponseEntity<>("目标未找到", HttpStatus.NOT_FOUND);
        } else {
            reviewService.deleteById(id);
            return new ResponseEntity<>("评论已删除", HttpStatus.OK);
        }
    }

    @PostMapping("/user")
    public List<Review> findByUser(@RequestBody User user) {
        return reviewService.findByUser(user);
    }
}

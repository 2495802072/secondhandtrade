package com.secondhandtrade.controller;

import com.secondhandtrade.model.Review;
import com.secondhandtrade.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
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
        reviewService.save(review);
        return review;
    }

    @GetMapping("/{id}")
    public Review getReview(@PathVariable int id) {}

}

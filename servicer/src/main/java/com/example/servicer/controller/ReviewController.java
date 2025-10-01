package com.example.servicer.controller;

import com.example.servicer.dto.review.ReviewRequest;
import com.example.servicer.entity.Review;
import com.example.servicer.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<Void> add(@Valid @RequestBody ReviewRequest request) {
        reviewService.addReview(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/service/{id}")
    public ResponseEntity<List<Review>> forService(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.forService(id));
    }

    @GetMapping("/provider/{id}")
    public ResponseEntity<List<Review>> forProvider(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.forProvider(id));
    }
}



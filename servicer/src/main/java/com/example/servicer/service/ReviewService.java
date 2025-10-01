package com.example.servicer.service;

import com.example.servicer.dto.review.ReviewRequest;
import com.example.servicer.entity.Booking;
import com.example.servicer.entity.BookingStatus;
import com.example.servicer.entity.Review;
import com.example.servicer.exception.ApiException;
import com.example.servicer.repository.BookingRepository;
import com.example.servicer.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final BookingRepository bookingRepository;

    public ReviewService(ReviewRepository reviewRepository, BookingRepository bookingRepository) {
        this.reviewRepository = reviewRepository;
        this.bookingRepository = bookingRepository;
    }

    public void addReview(ReviewRequest request) {
        Booking booking = bookingRepository.findById(request.getBookingId()).orElseThrow(() -> new ApiException("Booking not found"));
        if (booking.getStatus() != BookingStatus.COMPLETED) {
            throw new ApiException("Booking not completed");
        }
        Review review = Review.builder()
                .booking(booking)
                .rating(request.getRating())
                .comment(request.getComment())
                .build();
        reviewRepository.save(review);
    }

    public List<Review> forService(Long serviceId) {
        return reviewRepository.findByBooking_Service_Id(serviceId);
    }

    public List<Review> forProvider(Long providerId) {
        return reviewRepository.findByBooking_Provider_Id(providerId);
    }
}



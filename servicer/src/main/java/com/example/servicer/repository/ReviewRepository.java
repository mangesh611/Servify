package com.example.servicer.repository;

import com.example.servicer.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByBooking_Service_Id(Long serviceId);
    List<Review> findByBooking_Provider_Id(Long providerId);
}



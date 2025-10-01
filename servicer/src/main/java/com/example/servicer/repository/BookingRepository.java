package com.example.servicer.repository;

import com.example.servicer.entity.Booking;
import com.example.servicer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser(User user);
}



package com.example.servicer.controller;

import com.example.servicer.dto.booking.BookingRequest;
import com.example.servicer.dto.booking.BookingResponse;
import com.example.servicer.entity.BookingStatus;
import com.example.servicer.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<BookingResponse> create(@Valid @RequestBody BookingRequest request, Authentication auth) {
        return ResponseEntity.ok(bookingService.create(request, auth));
    }

    @GetMapping
    public ResponseEntity<List<BookingResponse>> listMine(Authentication auth) {
        return ResponseEntity.ok(bookingService.listMine(auth));
    }

    @PreAuthorize("hasAnyRole('PROVIDER','ADMIN')")
    @PatchMapping("/{id}/status")
    public ResponseEntity<BookingResponse> updateStatus(@PathVariable Long id, @RequestParam BookingStatus status) {
        return ResponseEntity.ok(bookingService.updateStatus(id, status));
    }
}



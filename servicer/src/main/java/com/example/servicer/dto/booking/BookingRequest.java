package com.example.servicer.dto.booking;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingRequest {
    @NotNull
    private Long providerId;
    @NotNull
    private Long serviceId;
    @NotNull
    @FutureOrPresent
    private LocalDateTime bookingDate;
}



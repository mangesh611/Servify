package com.example.servicer.dto.booking;

import com.example.servicer.entity.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class BookingResponse {
    private Long id;
    private Long userId;
    private Long providerId;
    private Long serviceId;
    private LocalDateTime bookingDate;
    private BookingStatus status;
}



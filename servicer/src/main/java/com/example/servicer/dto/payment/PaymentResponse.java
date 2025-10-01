package com.example.servicer.dto.payment;

import com.example.servicer.entity.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class PaymentResponse {
    private Long id;
    private Long bookingId;
    private BigDecimal amount;
    private PaymentStatus status;
    private LocalDateTime paymentDate;
}



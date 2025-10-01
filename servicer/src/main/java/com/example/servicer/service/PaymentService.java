package com.example.servicer.service;

import com.example.servicer.dto.payment.PaymentRequest;
import com.example.servicer.dto.payment.PaymentResponse;
import com.example.servicer.entity.*;
import com.example.servicer.exception.ApiException;
import com.example.servicer.repository.BookingRepository;
import com.example.servicer.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;

    public PaymentService(PaymentRepository paymentRepository, BookingRepository bookingRepository) {
        this.paymentRepository = paymentRepository;
        this.bookingRepository = bookingRepository;
    }

    public PaymentResponse pay(PaymentRequest request) {
        Booking booking = bookingRepository.findById(request.getBookingId()).orElseThrow(() -> new ApiException("Booking not found"));
        Payment payment = Payment.builder()
                .booking(booking)
                .amount(request.getAmount())
                .status(PaymentStatus.SUCCESS)
                .paymentDate(LocalDateTime.now())
                .build();
        payment = paymentRepository.save(payment);
        return PaymentResponse.builder()
                .id(payment.getId())
                .bookingId(payment.getBooking().getId())
                .amount(payment.getAmount())
                .status(payment.getStatus())
                .paymentDate(payment.getPaymentDate())
                .build();
    }

    public PaymentResponse get(Long id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new ApiException("Payment not found"));
        return PaymentResponse.builder()
                .id(payment.getId())
                .bookingId(payment.getBooking().getId())
                .amount(payment.getAmount())
                .status(payment.getStatus())
                .paymentDate(payment.getPaymentDate())
                .build();
    }
}



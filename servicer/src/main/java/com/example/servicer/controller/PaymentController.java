package com.example.servicer.controller;

import com.example.servicer.dto.payment.PaymentRequest;
import com.example.servicer.dto.payment.PaymentResponse;
import com.example.servicer.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentResponse> pay(@Valid @RequestBody PaymentRequest request) {
        return ResponseEntity.ok(paymentService.pay(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.get(id));
    }
}



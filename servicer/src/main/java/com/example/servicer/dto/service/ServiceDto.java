package com.example.servicer.dto.service;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ServiceDto {
    private Long id;

    @NotBlank
    private String name;

    private String description;

    @NotNull
    private BigDecimal basePrice;
}



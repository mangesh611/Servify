package com.example.servicer.dto.provider;

import com.example.servicer.dto.service.ServiceDto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class ProviderDto {
    private Long id;

    @NotBlank
    private String occupation;

    @Min(0)
    private int experience;

    private String description;

    private List<ServiceDto> servicesOffered;
}



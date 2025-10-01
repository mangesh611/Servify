package com.example.servicer.controller;

import com.example.servicer.dto.provider.ProviderDto;
import com.example.servicer.dto.service.ServiceDto;
import com.example.servicer.service.ProviderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/providers")
public class ProviderController {
    private final ProviderService providerService;

    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @GetMapping
    public ResponseEntity<List<ProviderDto>> list() {
        return ResponseEntity.ok(providerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProviderDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(providerService.findById(id));
    }

    @GetMapping("/{id}/services")
    public ResponseEntity<List<ServiceDto>> services(@PathVariable Long id) {
        return ResponseEntity.ok(providerService.getServices(id));
    }

    @PostMapping("/register")
    public ResponseEntity<ProviderDto> register(@Valid @RequestBody ProviderDto dto,
                                                @AuthenticationPrincipal UserDetails principal) {
        return ResponseEntity.ok(providerService.registerProviderByUsername(principal.getUsername(), dto));
    }
}



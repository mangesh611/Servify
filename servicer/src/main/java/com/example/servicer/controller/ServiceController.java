package com.example.servicer.controller;

import com.example.servicer.dto.service.ServiceDto;
import com.example.servicer.service.ServiceCatalogService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {
    private final ServiceCatalogService serviceCatalogService;

    public ServiceController(ServiceCatalogService serviceCatalogService) {
        this.serviceCatalogService = serviceCatalogService;
    }

    @GetMapping
    public ResponseEntity<List<ServiceDto>> list() {
        return ResponseEntity.ok(serviceCatalogService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(serviceCatalogService.findById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ServiceDto> create(@Valid @RequestBody ServiceDto dto) {
        return ResponseEntity.ok(serviceCatalogService.create(dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ServiceDto> update(@PathVariable Long id, @Valid @RequestBody ServiceDto dto) {
        return ResponseEntity.ok(serviceCatalogService.update(id, dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        serviceCatalogService.delete(id);
        return ResponseEntity.noContent().build();
    }
}



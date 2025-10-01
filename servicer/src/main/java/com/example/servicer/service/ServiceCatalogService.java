package com.example.servicer.service;

import com.example.servicer.dto.service.ServiceDto;
import com.example.servicer.entity.ServiceEntity;
import com.example.servicer.exception.ApiException;
import com.example.servicer.repository.ServiceEntityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceCatalogService {
    private final ServiceEntityRepository repository;

    public ServiceCatalogService(ServiceEntityRepository repository) {
        this.repository = repository;
    }

    public List<ServiceDto> findAll() {
        return repository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public ServiceDto findById(Long id) {
        ServiceEntity entity = repository.findById(id).orElseThrow(() -> new ApiException("Service not found"));
        return toDto(entity);
    }

    public ServiceDto create(ServiceDto dto) {
        ServiceEntity entity = new ServiceEntity();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setBasePrice(dto.getBasePrice());
        return toDto(repository.save(entity));
    }

    public ServiceDto update(Long id, ServiceDto dto) {
        ServiceEntity entity = repository.findById(id).orElseThrow(() -> new ApiException("Service not found"));
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setBasePrice(dto.getBasePrice());
        return toDto(repository.save(entity));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) throw new ApiException("Service not found");
        repository.deleteById(id);
    }

    private ServiceDto toDto(ServiceEntity e) {
        ServiceDto dto = new ServiceDto();
        dto.setId(e.getId());
        dto.setName(e.getName());
        dto.setDescription(e.getDescription());
        dto.setBasePrice(e.getBasePrice());
        return dto;
    }
}



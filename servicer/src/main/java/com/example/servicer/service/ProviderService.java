package com.example.servicer.service;

import com.example.servicer.dto.provider.ProviderDto;
import com.example.servicer.dto.service.ServiceDto;
import com.example.servicer.entity.Role;
import com.example.servicer.entity.ServiceEntity;
import com.example.servicer.entity.ServiceProvider;
import com.example.servicer.entity.User;
import com.example.servicer.exception.ApiException;
import com.example.servicer.repository.ServiceEntityRepository;
import com.example.servicer.repository.ServiceProviderRepository;
import com.example.servicer.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProviderService {
    private final ServiceProviderRepository providerRepository;
    private final ServiceEntityRepository serviceRepository;
    private final UserRepository userRepository;

    public ProviderService(ServiceProviderRepository providerRepository, ServiceEntityRepository serviceRepository, UserRepository userRepository) {
        this.providerRepository = providerRepository;
        this.serviceRepository = serviceRepository;
        this.userRepository = userRepository;
    }

    public List<ProviderDto> findAll() {
        return providerRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public ProviderDto findById(Long id) {
        ServiceProvider provider = providerRepository.findById(id).orElseThrow(() -> new ApiException("Provider not found"));
        return toDto(provider);
    }

    public List<ServiceDto> getServices(Long providerId) {
        ServiceProvider provider = providerRepository.findById(providerId).orElseThrow(() -> new ApiException("Provider not found"));
        return provider.getServicesOffered().stream().map(this::toServiceDto).collect(Collectors.toList());
    }

    @Transactional
    public ProviderDto registerProviderByUsername(String username, ProviderDto dto) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new ApiException("User not found"));
        if (user.getRole() == Role.PROVIDER) {
            throw new ApiException("User already a provider");
        }
        user.setRole(Role.PROVIDER);
        ServiceProvider provider = new ServiceProvider();
        provider.setUser(user);
        provider.setOccupation(dto.getOccupation());
        provider.setExperience(dto.getExperience());
        provider.setDescription(dto.getDescription());
        providerRepository.save(provider);
        return toDto(provider);
    }

    private ProviderDto toDto(ServiceProvider p) {
        ProviderDto dto = new ProviderDto();
        dto.setId(p.getId());
        dto.setOccupation(p.getOccupation());
        dto.setExperience(p.getExperience());
        dto.setDescription(p.getDescription());
        dto.setServicesOffered(p.getServicesOffered().stream().map(this::toServiceDto).collect(Collectors.toList()));
        return dto;
    }

    private ServiceDto toServiceDto(ServiceEntity e) {
        ServiceDto dto = new ServiceDto();
        dto.setId(e.getId());
        dto.setName(e.getName());
        dto.setDescription(e.getDescription());
        dto.setBasePrice(e.getBasePrice());
        return dto;
    }
}



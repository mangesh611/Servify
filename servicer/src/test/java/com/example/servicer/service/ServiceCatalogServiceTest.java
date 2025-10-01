package com.example.servicer.service;

import com.example.servicer.dto.service.ServiceDto;
import com.example.servicer.entity.ServiceEntity;
import com.example.servicer.repository.ServiceEntityRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class ServiceCatalogServiceTest {
    @Test
    void findAll_mapsEntitiesToDtos() {
        ServiceEntityRepository repo = Mockito.mock(ServiceEntityRepository.class);
        ServiceCatalogService service = new ServiceCatalogService(repo);

        ServiceEntity e = new ServiceEntity();
        e.setId(1L);
        e.setName("Cleaning");
        e.setBasePrice(new BigDecimal("50.00"));
        when(repo.findAll()).thenReturn(List.of(e));

        List<ServiceDto> list = service.findAll();
        assertThat(list).hasSize(1);
        assertThat(list.get(0).getName()).isEqualTo("Cleaning");
    }
}



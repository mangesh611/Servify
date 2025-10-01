package com.example.servicer.repository;

import com.example.servicer.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceEntityRepository extends JpaRepository<ServiceEntity, Long> {
}



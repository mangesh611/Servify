package com.example.servicer.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "service_providers")
public class ServiceProvider {
    @Id
    private Long id; // same as User id

    @OneToOne(optional = false)
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String occupation;

    @Min(0)
    @Column(nullable = false)
    private int experience;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToMany
    @JoinTable(
            name = "provider_services",
            joinColumns = @JoinColumn(name = "provider_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private List<ServiceEntity> servicesOffered = new ArrayList<>();
}



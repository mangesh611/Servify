package com.example.servicer.service;

import com.example.servicer.dto.booking.BookingRequest;
import com.example.servicer.dto.booking.BookingResponse;
import com.example.servicer.entity.*;
import com.example.servicer.exception.ApiException;
import com.example.servicer.repository.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ServiceProviderRepository providerRepository;
    private final ServiceEntityRepository serviceRepository;

    public BookingService(BookingRepository bookingRepository, UserRepository userRepository,
                          ServiceProviderRepository providerRepository, ServiceEntityRepository serviceRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.providerRepository = providerRepository;
        this.serviceRepository = serviceRepository;
    }

    public BookingResponse create(BookingRequest request, Authentication auth) {
        User user = userRepository.findByUsername(auth.getName()).orElseThrow(() -> new ApiException("User not found"));
        ServiceProvider provider = providerRepository.findById(request.getProviderId()).orElseThrow(() -> new ApiException("Provider not found"));
        ServiceEntity service = serviceRepository.findById(request.getServiceId()).orElseThrow(() -> new ApiException("Service not found"));

        Booking booking = Booking.builder()
                .user(user)
                .provider(provider)
                .service(service)
                .bookingDate(request.getBookingDate())
                .status(BookingStatus.PENDING)
                .build();
        booking = bookingRepository.save(booking);
        return toResponse(booking);
    }

    public List<BookingResponse> listMine(Authentication auth) {
        User user = userRepository.findByUsername(auth.getName()).orElseThrow(() -> new ApiException("User not found"));
        return bookingRepository.findByUser(user).stream().map(this::toResponse).collect(Collectors.toList());
    }

    public BookingResponse updateStatus(Long id, BookingStatus status) {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new ApiException("Booking not found"));
        booking.setStatus(status);
        return toResponse(bookingRepository.save(booking));
    }

    private BookingResponse toResponse(Booking b) {
        return BookingResponse.builder()
                .id(b.getId())
                .userId(b.getUser().getId())
                .providerId(b.getProvider().getId())
                .serviceId(b.getService().getId())
                .bookingDate(b.getBookingDate())
                .status(b.getStatus())
                .build();
    }
}



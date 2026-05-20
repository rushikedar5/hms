package com.hospital.hms.controller;

import com.hospital.hms.dto.ApiResponse;
import com.hospital.hms.dto.DoctorProfileResponseDto;
import com.hospital.hms.dto.DoctorRegistrationDto;
import com.hospital.hms.model.DoctorProfile;
import com.hospital.hms.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping("/doctors/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> register(@RequestBody @Valid DoctorRegistrationDto dto) {
        DoctorProfileResponseDto doctorProfile = doctorService.createDoctor(dto);

        return ResponseEntity
                .status(201)
                .body(ApiResponse.builder()
                        .data(doctorProfile)
                        .message("Doctor registered successfully!!")
                        .build());
    }

}

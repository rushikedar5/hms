package com.hospital.hms.controller;

import com.hospital.hms.dto.ApiResponse;
import com.hospital.hms.dto.PatientProfileDto;
import com.hospital.hms.dto.PatientProfileResponseDto;
import com.hospital.hms.service.PatientService;
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
public class PatientController {

    private final PatientService patientService;

    @PostMapping("/patients/profile")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<ApiResponse> register(@RequestBody @Valid PatientProfileDto dto) {
        PatientProfileResponseDto patientProfile = patientService.createPatient(dto);

        return ResponseEntity
                .status(201)
                .body(ApiResponse.builder()
                        .data(patientProfile)
                        .message("Patient registered successfully!!")
                        .build());
    }
}

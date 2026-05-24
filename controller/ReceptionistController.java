package com.hospital.hms.controller;

import com.hospital.hms.dto.*;
import com.hospital.hms.service.DoctorService;
import com.hospital.hms.service.PatientService;
import com.hospital.hms.service.ReceptionistService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ReceptionistController {

    private final ReceptionistService receptionistService;
    private final PatientService patientService;
    private final DoctorService doctorService;

    @PostMapping("/receptionist/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> create(@RequestBody @Valid ReceptionistProfileDto dto) {
        ReceptionistProfileResponseDto receptionistProfile = receptionistService.createReceptionist(dto);

        return ResponseEntity
                .status(201)
                .body(ApiResponse.builder()
                        .data(receptionistProfile)
                        .message("Receptionist registered successfully!!")
                        .build());
    }

    @GetMapping("/receptionist/patients")
    @PreAuthorize("hasRole('RECEPTIONIST')")
    public ResponseEntity<ApiResponse> getAllPatients() {
        List<PatientProfileResponseDto> patientProfile = patientService.getAllPatients();

        return ResponseEntity
                .status(201)
                .body(ApiResponse.builder()
                        .data(patientProfile)
                        .message("Patients fetched successfully!!")
                        .build());
    }

    @GetMapping("/receptionist/doctors")
    @PreAuthorize("hasRole('RECEPTIONIST')")
    public ResponseEntity<ApiResponse> getAllDoctors() {
        List<DoctorProfileResponseDto> doctorProfile = doctorService.getALlDoctors();

        return ResponseEntity
                .status(201)
                .body(ApiResponse.builder()
                        .data(doctorProfile)
                        .message("Doctors fetched successfully!!")
                        .build());
    }


}

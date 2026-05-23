package com.hospital.hms.controller;

import com.hospital.hms.dto.ApiResponse;
import com.hospital.hms.dto.AppointmentDto;
import com.hospital.hms.dto.AppointmentResponseDto;
import com.hospital.hms.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping("/appointments")
    @PreAuthorize("hasRole('PATIENT') or hasRole('RECEPTIONIST')")
    public ResponseEntity<ApiResponse> createAppointment(@RequestBody @Valid AppointmentDto dto) {
        AppointmentResponseDto appointment = appointmentService.createAppointment(dto);

        return ResponseEntity
                .status(201)
                .body(ApiResponse.builder()
                        .data(appointment)
                        .message("Appointment created successfully!!")
                        .build());
    }

    @GetMapping("/patients/appointments")
    public ResponseEntity<ApiResponse> getAppointments() {
        List<AppointmentResponseDto> appointment = appointmentService.getAppointments();

        return ResponseEntity
                .status(200)
                .body(ApiResponse.builder()
                        .data(appointment)
                        .message("Appointments fetched successfully!!")
                        .build());
    }
}

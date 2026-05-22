package com.hospital.hms.controller;

import com.hospital.hms.dto.ApiResponse;
import com.hospital.hms.dto.AppointmentDto;
import com.hospital.hms.model.Appointment;
import com.hospital.hms.service.AppointmentService;
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
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping("/appointments")
    @PreAuthorize("hasRole('PATIENT') or hasRole('RECEPTIONIST')")
    public ResponseEntity<ApiResponse> createAppointment(@RequestBody @Valid AppointmentDto dto) {
        Appointment appointment = appointmentService.createAppointment(dto);

        return ResponseEntity
                .status(201)
                .body(ApiResponse.builder()
                        .data(appointment)
                        .message("Appointment created successfully!!")
                        .build());
    }
}

package com.hospital.hms.controller;

import com.hospital.hms.dto.ApiResponse;
import com.hospital.hms.dto.DoctorScheduleDto;
import com.hospital.hms.model.DoctorSchedule;
import com.hospital.hms.service.DoctorScheduleService;
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
public class DoctorScheduleController {

    private final DoctorScheduleService service;

    @PostMapping("/doctors/schedule")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<ApiResponse> createSchedule(@RequestBody @Valid DoctorScheduleDto dto) {
        DoctorSchedule schedule = service.createSchedule(dto);

        return ResponseEntity
                .status(201)
                .body(ApiResponse.builder()
                        .data(schedule)
                        .message("Schedule created successfully!!")
                        .build());
    }
}

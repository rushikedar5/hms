package com.hospital.hms.controller;

import com.hospital.hms.dto.ApiResponse;
import com.hospital.hms.dto.MedicalRecordDto;
import com.hospital.hms.model.MedicalRecord;
import com.hospital.hms.service.MedicalRecordService;
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
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    @PostMapping("/medical-record")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<ApiResponse> createMedicalRecord(@RequestBody @Valid MedicalRecordDto dto) {
        MedicalRecord medicalRecord = medicalRecordService.addMedicalRecord(dto);

        return ResponseEntity
                .status(201)
                .body(ApiResponse.builder()
                        .data(medicalRecord)
                        .message("MedicalRecord created successfully!!")
                        .build());
    }
}

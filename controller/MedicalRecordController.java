package com.hospital.hms.controller;

import com.hospital.hms.dto.ApiResponse;
import com.hospital.hms.dto.MedicalRecordDto;
import com.hospital.hms.dto.MedicalRecordResponseDto;
import com.hospital.hms.model.MedicalRecord;
import com.hospital.hms.service.MedicalRecordService;
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
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    @PostMapping("/medical-records")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<ApiResponse> createMedicalRecord(@RequestBody @Valid MedicalRecordDto dto) {
        MedicalRecordResponseDto medicalRecord = medicalRecordService.addMedicalRecord(dto);

        return ResponseEntity
                .status(201)
                .body(ApiResponse.builder()
                        .data(medicalRecord)
                        .message("MedicalRecord created successfully!!")
                        .build());
    }

    @GetMapping("/patients/medical-records")
    public ResponseEntity<ApiResponse> getMedicalRecord() {
        List<MedicalRecordResponseDto> medicalRecord = medicalRecordService.getMedicalRecord();

        return ResponseEntity
                .status(200)
                .body(ApiResponse.builder()
                        .data(medicalRecord)
                        .message("MedicalRecord fetched successfully!!")
                        .build());
    }

    @GetMapping("/doctors/patients/{id}/records")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<ApiResponse> getMedicalRecordById(@PathVariable UUID id) {
        List<MedicalRecordResponseDto> medicalRecord = medicalRecordService.getMedicalRecordById(id);

        return ResponseEntity
                .status(200)
                .body(ApiResponse.builder()
                        .data(medicalRecord)
                        .message("MedicalRecord fetched successfully!!")
                        .build());
    }

}

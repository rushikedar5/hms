package com.hospital.hms.controller;

import com.hospital.hms.dto.ApiResponse;
import com.hospital.hms.dto.ReceptionistProfileDto;
import com.hospital.hms.dto.ReceptionistProfileResponseDto;
import com.hospital.hms.service.ReceptionistService;
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
public class ReceptionistController {

    private final ReceptionistService receptionistService;

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
}

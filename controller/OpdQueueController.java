package com.hospital.hms.controller;

import com.hospital.hms.dto.ApiResponse;
import com.hospital.hms.dto.OpdQueueDto;
import com.hospital.hms.model.OpdQueue;
import com.hospital.hms.service.OpdQueueService;
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
public class OpdQueueController {

    private final OpdQueueService opdQueueService;

    @PostMapping("/queue")
    @PreAuthorize("hasRole('RECEPTIONIST')")
    public ResponseEntity<ApiResponse> createAddToQueue(@RequestBody @Valid OpdQueueDto dto) {
        OpdQueue opdQueue = opdQueueService.addToQueue(dto);

        return ResponseEntity
                .status(201)
                .body(ApiResponse.builder()
                        .data(opdQueue)
                        .message("Added to queue successfully!!")
                        .build());
    }
}

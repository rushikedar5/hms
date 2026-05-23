package com.hospital.hms.controller;

import com.hospital.hms.dto.ApiResponse;
import com.hospital.hms.dto.OpdQueueDto;
import com.hospital.hms.dto.OpdQueueResponseDto;
import com.hospital.hms.dto.UpdateQueueStatusDto;
import com.hospital.hms.model.OpdQueue;
import com.hospital.hms.service.OpdQueueService;
import com.hospital.hms.service.UpdateQueueStatusService;
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
public class OpdQueueController {

    private final OpdQueueService opdQueueService;
    private final UpdateQueueStatusService updateQueueStatusService;

    @PostMapping("/queue")
    @PreAuthorize("hasRole('RECEPTIONIST')")
    public ResponseEntity<ApiResponse> createAddToQueue(@RequestBody @Valid OpdQueueDto dto) {
        OpdQueueResponseDto opdQueue = opdQueueService.addToQueue(dto);

        return ResponseEntity
                .status(201)
                .body(ApiResponse.builder()
                        .data(opdQueue)
                        .message("Added to queue successfully!!")
                        .build());
    }

    @PutMapping("/queue/{id}/status")
    @PreAuthorize("hasRole('RECEPTIONIST')")
    public ResponseEntity<ApiResponse> updateStatus(@PathVariable UUID id, @RequestBody @Valid UpdateQueueStatusDto dto){
        OpdQueue opdQueue = updateQueueStatusService.updateStatus(id, dto);

        return ResponseEntity
                .status(201)
                .body(ApiResponse.builder()
                        .data(opdQueue)
                        .message("QueueStatus updated successfully!!")
                        .build());
    }

    @GetMapping("doctors/queue")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<ApiResponse> getQueue() {
        List<OpdQueueResponseDto> opdQueue = opdQueueService.getQueue();

        return ResponseEntity
                .status(200)
                .body(ApiResponse.builder()
                        .data(opdQueue)
                        .message("Queues fetched successfully!!")
                        .build());
    }
}

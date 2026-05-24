package com.hospital.hms.service;

import com.hospital.hms.dto.OpdQueueResponseDto;
import com.hospital.hms.dto.UpdateQueueStatusDto;
import com.hospital.hms.model.OpdQueue;
import com.hospital.hms.repository.OpdQueueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateQueueStatusService {

    private final OpdQueueRepository opdQueueRepository;

    public OpdQueueResponseDto updateStatus(UUID queueId, UpdateQueueStatusDto dto) {
        OpdQueue queue = opdQueueRepository.findById(queueId)
                .orElseThrow(() -> new IllegalArgumentException("Queue not found"));

        queue.setQueueStatus(dto.getQueueStatus());

        return mapToResponse(opdQueueRepository.save(queue));
    }

    private OpdQueueResponseDto mapToResponse(OpdQueue opdQueue) {
        OpdQueueResponseDto response = new OpdQueueResponseDto();
        response.setId(opdQueue.getId());
        response.setDoctorName(opdQueue.getDoctorProfile().getName());
        response.setPatientName(opdQueue.getPatientProfile().getName());
        response.setQueueStatus(opdQueue.getQueueStatus());
        response.setTokenNo(opdQueue.getTokenNo());
        response.setAppointmentDate(opdQueue.getAppointment().getAppointmentDate());
        response.setCreatedAt(opdQueue.getCreatedAt());
        response.setUpdatedAt(opdQueue.getUpdatedAt());

        return response;
    }

}

package com.hospital.hms.service;

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

    public OpdQueue updateStatus(UUID queueId, UpdateQueueStatusDto dto) {
        OpdQueue queue = opdQueueRepository.findById(queueId)
                .orElseThrow(() -> new IllegalArgumentException("Queue not found"));

        queue.setQueueStatus(dto.getQueueStatus());

        return opdQueueRepository.save(queue);
    }

}

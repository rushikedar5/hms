package com.hospital.hms.dto;

import com.hospital.hms.enums.QueueStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpdQueueResponseDto {
    private UUID id;
    private String patientName;
    private String doctorName;
    private String tokenNo;
    private QueueStatus queueStatus;
    private LocalDate appointmentDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

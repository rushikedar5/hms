package com.hospital.hms.dto;

import com.hospital.hms.enums.AppointmentStatus;
import com.hospital.hms.enums.QueueStatus;
import com.hospital.hms.model.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentResponseDto {
    private UUID id;
    private String doctorName;
    private UUID doctorId;
    private String patientName;
    private String department;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private AppointmentStatus status;
    private LocalDateTime createdAt;

}

package com.hospital.hms.dto;

import com.hospital.hms.model.Appointment;
import com.hospital.hms.model.DoctorProfile;
import com.hospital.hms.model.PatientProfile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecordResponseDto {
    private String patientName;
    private String doctorName;
    private UUID appointmentId;
    private String height;
    private String weight;
    private String bp;
    private String symptoms;
    private String diagnosis;
    private String prescription;
    private String remarks;
    private LocalDateTime createdAt;
}

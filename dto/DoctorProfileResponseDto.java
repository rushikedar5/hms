package com.hospital.hms.dto;

import com.hospital.hms.model.Department;
import com.hospital.hms.model.DoctorProfile;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorProfileResponseDto {
    private UUID id;
    private String email;
    private String name;
    private String phone;
    private String department;
    private UUID departmentId;
    private String degree;
    private String licenseNo;
    private String specialization;
    private LocalDateTime createdAt;
}

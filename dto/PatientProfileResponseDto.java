package com.hospital.hms.dto;

import com.hospital.hms.enums.BloodGroup;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientProfileResponseDto {
    private UUID id;
    private String email;
    private String name;
    private BloodGroup bloodGroup;
    private String phone;
    private LocalDate dateOfBirth;
    private String address;
    private LocalDateTime createdAt;
}

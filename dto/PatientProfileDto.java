package com.hospital.hms.dto;

import com.hospital.hms.enums.BloodGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientProfileDto {
    @NotBlank
    private String name;
    @NotNull
    private BloodGroup bloodGroup;
    @NotBlank
    private String phone;
    @NotNull
    private LocalDate dateOfBirth;
    @NotBlank
    private String address;
}

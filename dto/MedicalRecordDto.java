package com.hospital.hms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecordDto {
    @NotNull
    private UUID patientId;
    @NotNull
    private UUID appointmentId;
    @NotBlank
    private String height;
    @NotBlank
    private String weight;
    @NotBlank
    private String bp;
    @NotBlank
    private String symptoms;
    @NotBlank
    private String diagnosis;
    @NotBlank
    private String prescription;
    @NotBlank
    private String remarks;
}

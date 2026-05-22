package com.hospital.hms.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpdQueueDto {
    @NotNull
    private UUID appointmentId;
    @NotNull
    private UUID patientId;
    @NotNull
    private UUID doctorId;
}

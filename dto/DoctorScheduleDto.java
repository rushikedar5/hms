package com.hospital.hms.dto;

import com.hospital.hms.enums.Days;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorScheduleDto {
    @NotNull
    private Days days;
    @NotNull
    private LocalTime startTime;
    @NotNull
    private LocalTime endTime;
    @NotNull
    private Boolean isAvailable;
}

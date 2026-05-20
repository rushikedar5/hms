package com.hospital.hms.dto;

import com.hospital.hms.model.Department;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorRegistrationDto {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String name;
    @NotBlank
    private String phone;
    @NotNull
    private UUID departmentId;
    @NotBlank
    private String degree;
    @NotBlank
    private String licenseNo;
    @NotBlank
    private String specialization;
}

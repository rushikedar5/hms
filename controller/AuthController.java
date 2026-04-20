package com.hospital.hms.controller;

import com.hospital.hms.dto.ApiResponse;
import com.hospital.hms.dto.AuthResponseDto;
import com.hospital.hms.dto.SignInDto;
import com.hospital.hms.dto.SignUpDto;
import com.hospital.hms.enums.Role;
import com.hospital.hms.model.User;
import com.hospital.hms.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register/patient")
    public ResponseEntity<ApiResponse> registerPatient(@RequestBody @Valid SignUpDto dto) {
        authService.signUp(dto, Role.PATIENT);

        return ResponseEntity
                .status(201)
                .body(ApiResponse.builder()
                        .message("User registered successfully")
                        .build());
    }

    @PostMapping("/register/doctor")
    public ResponseEntity<ApiResponse> registerDoctor(@RequestBody @Valid SignUpDto dto) {
        authService.signUp(dto, Role.DOCTOR);

        return ResponseEntity
                .status(201)
                .body(ApiResponse.builder()
                        .message("User registered successfully")
                        .build());
    }

    @PostMapping("/signin")
    public ResponseEntity<ApiResponse> signin(@RequestBody @Valid SignInDto dto){
        AuthResponseDto user = authService.signIn(dto);

        return ResponseEntity
                .status(200)
                .body(ApiResponse.builder()
                        .data(user)
                        .message("Signed In")
                        .build());
    }

}

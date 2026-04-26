package com.hospital.hms.controller;

import com.hospital.hms.dto.ApiResponse;
import com.hospital.hms.dto.DepartmentDto;
import com.hospital.hms.model.Department;
import com.hospital.hms.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping("/departments")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> createDepartments(@RequestBody @Valid DepartmentDto dto) {
        Department department = departmentService.createDepartment(dto);

        return ResponseEntity
                .status(201)
                .body(ApiResponse.builder()
                        .data(department)
                        .message("Department created!!")
                        .build());
    }

    @GetMapping("/departments")
    public ResponseEntity<ApiResponse> getAllDepartment() {
        List<Department> departments = departmentService.getAllDepartments();

        return ResponseEntity
                .status(200)
                .body(ApiResponse.builder()
                        .data(departments)
                        .message("Fetched all departments!!")
                        .build());
    }

}

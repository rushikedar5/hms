package com.hospital.hms.service;

import com.hospital.hms.dto.DepartmentDto;
import com.hospital.hms.enums.DepartmentStatus;
import com.hospital.hms.model.Department;
import com.hospital.hms.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public Department createDepartment(DepartmentDto dto) {

        if(departmentRepository.findByName(dto.getName()).isPresent()) {
            throw new IllegalArgumentException("Department already exits!");
        }

        Department department = new Department();
        department.setName(dto.getName());
        department.setStatus(DepartmentStatus.ACTIVE);

        return departmentRepository.save(department);
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

}

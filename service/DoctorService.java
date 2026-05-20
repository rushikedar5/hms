package com.hospital.hms.service;

import com.hospital.hms.dto.DoctorProfileResponseDto;
import com.hospital.hms.dto.DoctorRegistrationDto;
import com.hospital.hms.enums.Role;
import com.hospital.hms.model.Department;
import com.hospital.hms.model.DoctorProfile;
import com.hospital.hms.model.User;
import com.hospital.hms.repository.DepartmentRepository;
import com.hospital.hms.repository.DoctorProfileRepository;
import com.hospital.hms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorProfileRepository doctorProfileRepository;
    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public DoctorProfileResponseDto createDoctor(DoctorRegistrationDto dto) {

        if(userRepository.findByEmail(dto.getEmail()).isPresent()){
            throw new IllegalArgumentException("User already exist!!");
        }

        String hashedPass = passwordEncoder.encode(dto.getPassword());

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(hashedPass);
        user.setRole(Role.DOCTOR);

        userRepository.save(user);

        Department department = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new IllegalArgumentException("Department not found!!"));

        DoctorProfile doctorProfile = new DoctorProfile();
        doctorProfile.setUser(user);
        doctorProfile.setName(dto.getName());
        doctorProfile.setDepartment(department);
        doctorProfile.setPhone(dto.getPhone());
        doctorProfile.setDegree(dto.getDegree());
        doctorProfile.setSpecialization(dto.getSpecialization());
        doctorProfile.setLicenseNo(dto.getLicenseNo());

        DoctorProfile saved = doctorProfileRepository.save(doctorProfile);

        DoctorProfileResponseDto response = new DoctorProfileResponseDto();
        response.setId(saved.getId());
        response.setEmail(saved.getUser().getEmail());
        response.setName(saved.getName());
        response.setDepartment(department.getName());
        response.setDepartmentId(department.getId());
        response.setPhone(saved.getPhone());
        response.setDegree(saved.getDegree());
        response.setSpecialization(saved.getSpecialization());
        response.setLicenseNo(saved.getLicenseNo());
        response.setCreatedAt(saved.getCreatedAt());

        return response;
    }
}

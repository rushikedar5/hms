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

import java.util.List;

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

        return mapToResponse(doctorProfileRepository.save(doctorProfile));

    }

    public List<DoctorProfileResponseDto> getALlDoctors() {
        return doctorProfileRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public DoctorProfileResponseDto mapToResponse(DoctorProfile doctorProfile) {
        DoctorProfileResponseDto response = new DoctorProfileResponseDto();
        response.setId(doctorProfile.getId());
        response.setEmail(doctorProfile.getUser().getEmail());
        response.setName(doctorProfile.getName());
        response.setDepartment(doctorProfile.getName());
        response.setDepartmentId(doctorProfile.getId());
        response.setPhone(doctorProfile.getPhone());
        response.setDegree(doctorProfile.getDegree());
        response.setSpecialization(doctorProfile.getSpecialization());
        response.setLicenseNo(doctorProfile.getLicenseNo());
        response.setCreatedAt(doctorProfile.getCreatedAt());

        return response;
    }
}

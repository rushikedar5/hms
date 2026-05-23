package com.hospital.hms.service;

import com.hospital.hms.dto.PatientProfileDto;
import com.hospital.hms.dto.PatientProfileResponseDto;
import com.hospital.hms.model.PatientProfile;
import com.hospital.hms.model.User;
import com.hospital.hms.repository.PatientProfileRepository;
import com.hospital.hms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final UserRepository userRepository;
    private final PatientProfileRepository patientProfileRepository;

    public PatientProfileResponseDto createPatient(PatientProfileDto dto) {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));

        if(patientProfileRepository.findByUser(user).isPresent()) {
            throw new IllegalArgumentException("Profile already exists!");
        }

        PatientProfile patientProfile = new PatientProfile();
        patientProfile.setUser(user);
        patientProfile.setName(dto.getName());
        patientProfile.setPhone(dto.getPhone());
        patientProfile.setAddress(dto.getAddress());
        patientProfile.setBloodGroup(dto.getBloodGroup());
        patientProfile.setDateOfBirth(dto.getDateOfBirth());

        return mapToResponse(patientProfileRepository.save(patientProfile));

    }

    public List<PatientProfileResponseDto> getAllPatients() {
        return patientProfileRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public PatientProfileResponseDto mapToResponse(PatientProfile patientProfile) {
        PatientProfileResponseDto response = new PatientProfileResponseDto();
        response.setId(patientProfile.getId());
        response.setName(patientProfile.getName());
        response.setEmail(patientProfile.getUser().getEmail());
        response.setAddress(patientProfile.getAddress());
        response.setPhone(patientProfile.getPhone());
        response.setDateOfBirth(patientProfile.getDateOfBirth());
        response.setBloodGroup(patientProfile.getBloodGroup());
        response.setCreatedAt(patientProfile.getCreatedAt());

        return response;

    }
}

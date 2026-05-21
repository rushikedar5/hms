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

        patientProfileRepository.save(patientProfile);

        PatientProfileResponseDto response = new PatientProfileResponseDto();
        response.setId(user.getId());
        response.setEmail(email);
        response.setName(patientProfile.getName());
        response.setPhone(patientProfile.getPhone());
        response.setAddress(patientProfile.getAddress());
        response.setBloodGroup(patientProfile.getBloodGroup());
        response.setDateOfBirth(patientProfile.getDateOfBirth());
        response.setCreatedAt(patientProfile.getCreatedAt());

        return response;
    }
}

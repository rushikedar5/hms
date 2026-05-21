package com.hospital.hms.service;

import com.hospital.hms.dto.ReceptionistProfileDto;
import com.hospital.hms.dto.ReceptionistProfileResponseDto;
import com.hospital.hms.enums.Role;
import com.hospital.hms.model.ReceptionistProfile;
import com.hospital.hms.model.User;
import com.hospital.hms.repository.ReceptionistProfileRepository;
import com.hospital.hms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReceptionistService {

    private final ReceptionistProfileRepository receptionistProfileRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ReceptionistProfileResponseDto createReceptionist(ReceptionistProfileDto dto) {

        if(userRepository.findByEmail(dto.getEmail()).isPresent()){
            throw new IllegalArgumentException("User already exits!!");
        }

        String hashPass = passwordEncoder.encode(dto.getPassword());

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(hashPass);
        user.setRole(Role.RECEPTIONIST);

        userRepository.save(user);

        ReceptionistProfile receptionistProfile = new ReceptionistProfile();
        receptionistProfile.setUser(user);
        receptionistProfile.setName(dto.getName());
        receptionistProfile.setPhone(dto.getPhone());

        receptionistProfileRepository.save(receptionistProfile);

        ReceptionistProfileResponseDto response = new ReceptionistProfileResponseDto();
        response.setId(receptionistProfile.getId());
        response.setEmail(user.getEmail());
        response.setName(receptionistProfile.getName());
        response.setPhone(receptionistProfile.getPhone());

        return response;
    }


}

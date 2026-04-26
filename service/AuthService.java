package com.hospital.hms.service;

import com.hospital.hms.dto.AuthResponseDto;
import com.hospital.hms.dto.SignInDto;
import com.hospital.hms.dto.SignUpDto;
import com.hospital.hms.enums.Role;
import com.hospital.hms.lib.JwtUtil;
import com.hospital.hms.model.User;
import com.hospital.hms.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public void signUp(SignUpDto dto, Role role) {

            if(userRepository.findByEmail(dto.getEmail()).isPresent()) {
                throw new IllegalArgumentException("Email already exits!");
            }

            String hashedPassword = passwordEncoder.encode(dto.getPassword());

            User user = new User();
            user.setEmail(dto.getEmail());
            user.setPassword(hashedPassword);
            user.setRole(role);

            userRepository.save(user);
    }

    public AuthResponseDto signIn(SignInDto dto, Role role) {

        User user = (userRepository.findByEmail(dto.getEmail())
                        .orElseThrow(() -> new UsernameNotFoundException("User not found!!")));

        boolean pass = passwordEncoder.matches(dto.getPassword(), user.getPassword());

        if(!pass) {
            throw new IllegalArgumentException("Wrong Password!!");
        }

        String token = jwtUtil.generateToken(dto.getEmail(), user.getRole());

        return new AuthResponseDto(token);
    }
}

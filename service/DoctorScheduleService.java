package com.hospital.hms.service;

import com.hospital.hms.dto.DoctorScheduleDto;
import com.hospital.hms.enums.Days;
import com.hospital.hms.model.DoctorProfile;
import com.hospital.hms.model.DoctorSchedule;
import com.hospital.hms.model.User;
import com.hospital.hms.repository.DoctorProfileRepository;
import com.hospital.hms.repository.DoctorScheduleRepository;
import com.hospital.hms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class DoctorScheduleService {

    private final DoctorScheduleRepository doctorScheduleRepository;
    private final UserRepository userRepository;
    private final DoctorProfileRepository doctorProfileRepository;

    public DoctorSchedule createSchedule(DoctorScheduleDto dto) {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!!"));

        DoctorProfile profile = doctorProfileRepository.findByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found"));

        if(doctorScheduleRepository.findByDoctorProfileAndDays(profile, dto.getDays()).isPresent()) {
            throw new IllegalArgumentException("Schedule already exists for this day!");
        }

        DoctorSchedule schedule = new DoctorSchedule();
        schedule.setDoctorProfile(profile);
        schedule.setIsAvailable(dto.getIsAvailable());
        schedule.setDays(dto.getDays());
        schedule.setStartTime(dto.getStartTime());
        schedule.setEndTime(dto.getEndTime());

        return doctorScheduleRepository.save(schedule);

    }

}

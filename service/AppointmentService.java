package com.hospital.hms.service;

import com.hospital.hms.dto.AppointmentDto;
import com.hospital.hms.enums.AppointmentStatus;
import com.hospital.hms.enums.Role;
import com.hospital.hms.model.Appointment;
import com.hospital.hms.model.DoctorProfile;
import com.hospital.hms.model.PatientProfile;
import com.hospital.hms.model.User;
import com.hospital.hms.repository.AppointmentRepository;
import com.hospital.hms.repository.DoctorProfileRepository;
import com.hospital.hms.repository.PatientProfileRepository;
import com.hospital.hms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final PatientProfileRepository patientProfileRepository;
    private final DoctorProfileRepository doctorProfileRepository;


    public Appointment createAppointment(AppointmentDto dto) {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found!!"));

        if(user.getRole() == Role.PATIENT){
            PatientProfile patientProfile = patientProfileRepository.findByUser(user)
                    .orElseThrow(() -> new IllegalArgumentException("Patient not found!!"));

            DoctorProfile doctorProfile = doctorProfileRepository.findById(dto.getDoctorId())
                    .orElseThrow(() -> new IllegalArgumentException("Doctor not found!!"));

            Appointment appointment = new Appointment();
            appointment.setPatientProfile(patientProfile);
            appointment.setAppointmentDate(dto.getAppointmentDate());
            appointment.setAppointmentStatus(AppointmentStatus.BOOKED);
            appointment.setAppointmentTime(dto.getAppointmentTime());
            appointment.setDoctorProfile(doctorProfile);

           return appointmentRepository.save(appointment);

        } else if (user.getRole() == Role.RECEPTIONIST) {
            if(dto.getPatientId() == null) {
                throw new IllegalArgumentException("Patient ID is required for receptionist booking");
            }

            PatientProfile patientProfile = patientProfileRepository.findById(dto.getPatientId())
                    .orElseThrow(() -> new IllegalArgumentException("Patient not found!"));

            DoctorProfile doctorProfile = doctorProfileRepository.findById(dto.getDoctorId())
                    .orElseThrow(() -> new IllegalArgumentException("Doctor not found!!"));

            Appointment appointment = new Appointment();
            appointment.setPatientProfile(patientProfile);
            appointment.setDoctorProfile(doctorProfile);
            appointment.setAppointmentDate(dto.getAppointmentDate());
            appointment.setAppointmentStatus(AppointmentStatus.BOOKED);
            appointment.setAppointmentTime(dto.getAppointmentTime());

            return appointmentRepository.save(appointment);
        }

        throw new IllegalArgumentException("Unauthorized role for booking appointments");
    }
}

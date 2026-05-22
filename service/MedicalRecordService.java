package com.hospital.hms.service;

import com.hospital.hms.dto.MedicalRecordDto;
import com.hospital.hms.model.*;
import com.hospital.hms.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.net.ContentHandler;

@Service
@RequiredArgsConstructor
public class MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;
    private final AppointmentRepository appointmentRepository;
    private final PatientProfileRepository patientProfileRepository;
    private final DoctorProfileRepository doctorProfileRepository;
    private final UserRepository userRepository;

    public MedicalRecord addMedicalRecord(MedicalRecordDto dto) {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found!!"));

        DoctorProfile doctorProfile = doctorProfileRepository.findByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found!!"));

        PatientProfile patientProfile = patientProfileRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new IllegalArgumentException("Patient not found!!"));

        Appointment appointment = appointmentRepository.findById(dto.getAppointmentId())
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found!!"));

        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setPatientProfile(patientProfile);
        medicalRecord.setDoctorProfile(doctorProfile);
        medicalRecord.setAppointment(appointment);
        medicalRecord.setHeight(dto.getHeight());
        medicalRecord.setWeight(dto.getWeight());
        medicalRecord.setBp(dto.getBp());
        medicalRecord.setSymptoms(dto.getSymptoms());
        medicalRecord.setDiagnosis(dto.getDiagnosis());
        medicalRecord.setPrescription(dto.getPrescription());
        medicalRecord.setRemarks(dto.getRemarks());

        return medicalRecordRepository.save(medicalRecord);

    }
}

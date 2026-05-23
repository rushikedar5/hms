package com.hospital.hms.service;

import com.hospital.hms.dto.AppointmentResponseDto;
import com.hospital.hms.dto.MedicalRecordDto;
import com.hospital.hms.dto.MedicalRecordResponseDto;
import com.hospital.hms.model.*;
import com.hospital.hms.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.net.ContentHandler;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;
    private final AppointmentRepository appointmentRepository;
    private final PatientProfileRepository patientProfileRepository;
    private final DoctorProfileRepository doctorProfileRepository;
    private final UserRepository userRepository;

    public MedicalRecordResponseDto addMedicalRecord(MedicalRecordDto dto) {

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

        return mapToResponse(medicalRecordRepository.save(medicalRecord));

    }

    public List<MedicalRecordResponseDto> getMedicalRecord() {
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found!!"));

        PatientProfile patientProfile = patientProfileRepository.findByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found!!"));

        return medicalRecordRepository.findByPatientProfile(patientProfile)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<MedicalRecordResponseDto> getMedicalRecordById(UUID id) {

        PatientProfile patientProfile = patientProfileRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found!!"));

        return medicalRecordRepository.findByPatientProfile(patientProfile)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    private MedicalRecordResponseDto mapToResponse(MedicalRecord medicalRecord) {
        MedicalRecordResponseDto response = new MedicalRecordResponseDto();
        response.setPatientName(medicalRecord.getPatientProfile().getName());
        response.setDoctorName(medicalRecord.getDoctorProfile().getName());
        response.setAppointmentId(medicalRecord.getAppointment().getId());
        response.setHeight(medicalRecord.getHeight());
        response.setWeight(medicalRecord.getWeight());
        response.setBp(medicalRecord.getBp());
        response.setSymptoms(medicalRecord.getSymptoms());
        response.setDiagnosis(medicalRecord.getDiagnosis());
        response.setPrescription(medicalRecord.getPrescription());
        response.setRemarks(medicalRecord.getRemarks());
        return response;
    }


}

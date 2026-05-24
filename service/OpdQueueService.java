package com.hospital.hms.service;

import com.hospital.hms.dto.OpdQueueDto;
import com.hospital.hms.dto.OpdQueueResponseDto;
import com.hospital.hms.enums.QueueStatus;
import com.hospital.hms.model.*;
import com.hospital.hms.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OpdQueueService {

    private final UserRepository userRepository;
    private final PatientProfileRepository patientProfileRepository;
    private final DoctorProfileRepository doctorProfileRepository;
    private final AppointmentRepository appointmentRepository;
    private final OpdQueueRepository opdQueueRepository;

    public OpdQueueResponseDto addToQueue(OpdQueueDto dto) {
        PatientProfile patientProfile = patientProfileRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new IllegalArgumentException("Patient not found!!"));

        DoctorProfile doctorProfile = doctorProfileRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found!!"));

        Appointment appointment = appointmentRepository.findById(dto.getAppointmentId())
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found!!"));

        opdQueueRepository.findByAppointment(appointment).ifPresent(q -> {
            if(q.getQueueStatus() != QueueStatus.CANCELLED &&
                    q.getQueueStatus() != QueueStatus.COMPLETED) {
                throw new IllegalArgumentException("Patient already in queue for this appointment!!");
            }
        });

        LocalDate today = LocalDate.now();
        int count = opdQueueRepository.countByCreatedAtDate(today);
        String tokenNo = String.format("%02d", count + 1);

        OpdQueue opdQueue = new OpdQueue();
        opdQueue.setAppointment(appointment);
        opdQueue.setDoctorProfile(doctorProfile);
        opdQueue.setPatientProfile(patientProfile);
        opdQueue.setQueueStatus(QueueStatus.WAITING);
        opdQueue.setTokenNo(tokenNo);

        return mapToResponse(opdQueueRepository.save(opdQueue));
    }

    public List<OpdQueueResponseDto> getQueue() {
        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found!!"));

        DoctorProfile doctorProfile = doctorProfileRepository.findByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found!!"));

        return opdQueueRepository.findByDoctorProfile(doctorProfile)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<OpdQueueResponseDto> getAllQueue() {
        return opdQueueRepository.findAllByOrderByTokenNoAsc()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public OpdQueueResponseDto getPatientQueue() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        PatientProfile patient = patientProfileRepository.findByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("Profile not found"));

        return opdQueueRepository
                .findFirstByPatientProfileAndQueueStatusIn(
                        patient,
                        List.of(QueueStatus.WAITING, QueueStatus.IN_PROGRESS)
                )
                .map(this::mapToResponse)
                .orElse(null);
    }

    private OpdQueueResponseDto mapToResponse(OpdQueue opdQueue) {
        OpdQueueResponseDto response = new OpdQueueResponseDto();
        response.setId(opdQueue.getId());
        response.setDoctorName(opdQueue.getDoctorProfile().getName());
        response.setPatientName(opdQueue.getPatientProfile().getName());
        response.setPatientId(opdQueue.getPatientProfile().getId());
        response.setQueueStatus(opdQueue.getQueueStatus());
        response.setTokenNo(opdQueue.getTokenNo());
        response.setAppointmentDate(opdQueue.getAppointment().getAppointmentDate());
        response.setAppointmentId(opdQueue.getAppointment().getId());
        response.setCreatedAt(opdQueue.getCreatedAt());
        response.setUpdatedAt(opdQueue.getUpdatedAt());

        return response;
    }


}

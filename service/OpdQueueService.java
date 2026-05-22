package com.hospital.hms.service;

import com.hospital.hms.dto.OpdQueueDto;
import com.hospital.hms.enums.QueueStatus;
import com.hospital.hms.model.Appointment;
import com.hospital.hms.model.DoctorProfile;
import com.hospital.hms.model.OpdQueue;
import com.hospital.hms.model.PatientProfile;
import com.hospital.hms.repository.AppointmentRepository;
import com.hospital.hms.repository.DoctorProfileRepository;
import com.hospital.hms.repository.OpdQueueRepository;
import com.hospital.hms.repository.PatientProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class OpdQueueService {

    private final PatientProfileRepository patientProfileRepository;
    private final DoctorProfileRepository doctorProfileRepository;
    private final AppointmentRepository appointmentRepository;
    private final OpdQueueRepository opdQueueRepository;

    public OpdQueue addToQueue(OpdQueueDto dto) {
        PatientProfile patientProfile = patientProfileRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new IllegalArgumentException("Patient not found!!"));

        DoctorProfile doctorProfile = doctorProfileRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found!!"));

        Appointment appointment = appointmentRepository.findById(dto.getAppointmentId())
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found!!"));

        if(opdQueueRepository.findByAppointment(appointment).isPresent()){
            throw new IllegalArgumentException("Patient already in queue for this appointment!!");
        }

        LocalDate today = LocalDate.now();
        String tokenNo = opdQueueRepository.countByDoctorProfile(doctorProfile) + 1;

        OpdQueue opdQueue = new OpdQueue();
        opdQueue.setAppointment(appointment);
        opdQueue.setDoctorProfile(doctorProfile);
        opdQueue.setPatientProfile(patientProfile);
        opdQueue.setQueueStatus(QueueStatus.WAITING);
        opdQueue.setTokenNo(tokenNo);

        return opdQueueRepository.save(opdQueue);
    }
}

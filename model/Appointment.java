package com.hospital.hms.model;

import com.hospital.hms.enums.AppointmentStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private PatientProfile patientProfile;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private DoctorProfile doctorProfile;

    private LocalDate appointmentDate;

    private LocalTime appointmentTime;

    @Enumerated(value = EnumType.STRING)
    private AppointmentStatus appointmentStatus;

    @CreationTimestamp
    private LocalDateTime createdAt;
}

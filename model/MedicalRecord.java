package com.hospital.hms.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "medical_records")
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private PatientProfile patientProfile;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private DoctorProfile doctorProfile;

    @ManyToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    private String height;

    private String weight;

    private String bp;

    private String symptoms;

    private String diagnosis;

    private String prescription;

    private String remarks;

    @CreationTimestamp
    private LocalDateTime createdAt;
}

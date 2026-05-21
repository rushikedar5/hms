package com.hospital.hms.model;

import com.hospital.hms.enums.Days;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "doctor_schedules")
public class DoctorSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private DoctorProfile doctorProfile;

    @Enumerated(value = EnumType.STRING)
    private Days days;

    private LocalTime startTime;

    private LocalTime endTime;

    private Boolean isAvailable;

    @CreationTimestamp
    private LocalDateTime createdAt;
}

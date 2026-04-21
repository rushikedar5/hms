package com.hospital.hms.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "doctor_profiles")
@Entity
@Data
public class DoctorProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    private String licenseNo;

    private String specialization;

    private String degree;

    private String phone;

    @CreationTimestamp
    private LocalDateTime createdAt;
}

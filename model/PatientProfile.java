package com.hospital.hms.model;

import com.hospital.hms.enums.BloodGroup;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Table(name = "patient_profiles")
@Entity
@Data
public class PatientProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String name;

    @Enumerated(value = EnumType.STRING)
    private BloodGroup bloodGroup;

    private String phone;

    private LocalDate dateOfBirth;

    private String address;

    @CreationTimestamp
    private LocalDateTime createdAt;

}

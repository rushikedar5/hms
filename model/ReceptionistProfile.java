package com.hospital.hms.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "receptionist_profiles")
@Entity
@Data
public class ReceptionistProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String name;

    private String phone;

    @CreationTimestamp
    private LocalDateTime createdAt;

}

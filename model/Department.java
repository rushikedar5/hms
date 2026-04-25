package com.hospital.hms.model;

import com.hospital.hms.enums.DepartmentStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "departments", uniqueConstraints = {
        @UniqueConstraint(name = "UniqueName", columnNames = {"name"})
})
@Entity
@Data
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @Enumerated(value = EnumType.STRING)
    private DepartmentStatus status;

    @CreationTimestamp
    private LocalDateTime createdAt;
}

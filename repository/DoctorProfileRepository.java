package com.hospital.hms.repository;

import com.hospital.hms.model.DoctorProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DoctorProfileRepository extends JpaRepository<DoctorProfile, UUID> {
}

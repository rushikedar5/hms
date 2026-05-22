package com.hospital.hms.repository;

import com.hospital.hms.model.DoctorProfile;
import com.hospital.hms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DoctorProfileRepository extends JpaRepository<DoctorProfile, UUID> {
    Optional<DoctorProfile> findByUser(User user);
}

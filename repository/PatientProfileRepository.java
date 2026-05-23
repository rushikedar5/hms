package com.hospital.hms.repository;

import com.hospital.hms.model.MedicalRecord;
import com.hospital.hms.model.PatientProfile;
import com.hospital.hms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PatientProfileRepository extends JpaRepository<PatientProfile, UUID> {
    Optional<PatientProfile> findByUser(User user);


}

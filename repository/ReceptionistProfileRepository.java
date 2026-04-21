package com.hospital.hms.repository;

import com.hospital.hms.model.ReceptionistProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReceptionistProfileRepository extends JpaRepository<ReceptionistProfile, UUID> {
}

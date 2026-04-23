package com.hospital.hms.repository;

import com.hospital.hms.model.DoctorSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DoctorScheduleRepository extends JpaRepository<DoctorSchedule, UUID> {
}

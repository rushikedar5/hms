package com.hospital.hms.repository;

import com.hospital.hms.model.Appointment;
import com.hospital.hms.model.DoctorProfile;
import com.hospital.hms.model.OpdQueue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OpdQueueRepository extends JpaRepository<OpdQueue, UUID> {

    Optional<OpdQueue> findByAppointment(Appointment appointment);

    String countByDoctorProfile(DoctorProfile doctorProfile);
}

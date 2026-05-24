package com.hospital.hms.repository;

import aj.org.objectweb.asm.commons.Remapper;
import com.hospital.hms.enums.QueueStatus;
import com.hospital.hms.model.Appointment;
import com.hospital.hms.model.DoctorProfile;
import com.hospital.hms.model.OpdQueue;
import com.hospital.hms.model.PatientProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OpdQueueRepository extends JpaRepository<OpdQueue, UUID> {

    Optional<OpdQueue> findByAppointment(Appointment appointment);

    @Query("SELECT COUNT(q) FROM OpdQueue q WHERE DATE(q.createdAt) = :date")
    int countByCreatedAtDate(@Param("date") LocalDate date);

    List<OpdQueue> findAllByOrderByTokenNoAsc();

    List<OpdQueue> findByDoctorProfile(DoctorProfile doctorProfile);

    Optional<OpdQueue> findFirstByPatientProfileAndQueueStatusIn(PatientProfile patientProfile, List<QueueStatus> statuses);
}

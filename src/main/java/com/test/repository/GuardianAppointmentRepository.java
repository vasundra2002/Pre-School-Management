package com.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.model.GuardianAppointment;

@Repository
public interface GuardianAppointmentRepository extends JpaRepository<GuardianAppointment, Long> {
	public List<GuardianAppointment> findByGuardianName(String guardianName);
    public List<GuardianAppointment> findByChildName(String childName);
}

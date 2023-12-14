package com.test.service;

import java.util.List;

import com.test.model.GuardianAppointment;





public interface GuardianAppointmentService {
	public GuardianAppointment addGuardianAppointment(GuardianAppointment guardianAppointment);
	public List<GuardianAppointment> getAllGuardianAppointments();
	public void deleteGuardianAppointmentById(long id);
	 GuardianAppointment saveGuardianAppointment(GuardianAppointment guardianAppointment) throws Exception;
}

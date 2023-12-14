package com.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.model.GuardianAppointment;
import com.test.repository.GuardianAppointmentRepository;


@Service
public class GuardianAppointmentServiceImplementation implements GuardianAppointmentService {
	
	@Autowired
	private  GuardianAppointmentRepository guradianAppointmentRepository;

	@Override
	public GuardianAppointment addGuardianAppointment(GuardianAppointment guardianAppointment) {
		
		 return  guradianAppointmentRepository.save(guardianAppointment);
	}

	@Override
	public List<GuardianAppointment> getAllGuardianAppointments() {
		
		return guradianAppointmentRepository.findAll();
	}

	@Override
	public GuardianAppointment saveGuardianAppointment(GuardianAppointment guardianAppointment) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteGuardianAppointmentById(long id) {
		guradianAppointmentRepository.deleteById(id);
		
	}
}

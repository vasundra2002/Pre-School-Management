package com.test.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.model.GuardianAppointment;
import com.test.repository.GuardianAppointmentRepository;
import com.test.service.GuardianAppointmentService;


@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class GuardianAppointmentController {
	@Autowired
	private GuardianAppointmentService guardianAppointmentService;
	
	@Autowired
	private GuardianAppointmentRepository guardianAppointmentRepository;

	@PostMapping("/appointment")
	public GuardianAppointment saveGuardianAppointmentRecord(@Validated @RequestBody GuardianAppointment guardianAppointment) //request handler method
	{
		return this.guardianAppointmentService.addGuardianAppointment(guardianAppointment);
	}

	@GetMapping("/appointments")
    
	public List<GuardianAppointment> getGuardianAppointment()
	{
		return this.guardianAppointmentService.getAllGuardianAppointments();
	}
	
	@DeleteMapping(path = "/appointment/{id}")
	public ResponseEntity<HashMap> deleteGuardianAppointmentt(@PathVariable("id") long id) {
	    HashMap<String, String> response = new HashMap<>();

	    if (guardianAppointmentRepository.findById(id).isPresent()) {
	        guardianAppointmentService.deleteGuardianAppointmentById(id);
	        response.put("message", "Record deleted successfully!!");
	        return new ResponseEntity<HashMap>(response, HttpStatus.OK);
	    } else {
	        response.put("message", "Invalid appointment id!!");
	        return new ResponseEntity<HashMap>(response, HttpStatus.NOT_FOUND);
	    }
	}

	
	
	

	
}




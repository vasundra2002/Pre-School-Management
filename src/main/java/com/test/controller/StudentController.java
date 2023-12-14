package com.test.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.test.model.Role;
import com.test.model.Student;
import com.test.repository.RoleRepository;
import com.test.repository.StudentRepository;

@RestController
@RequestMapping("/student/auth")
@CrossOrigin(origins = "http://localhost:4200/")
public class StudentController {
	
	@Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @PostMapping("/signup")
    public ResponseEntity<?> studentSignup(@RequestBody Student student )
    {
        HashMap<String , String> response=new HashMap<>();
        if(studentRepository.existsByUsername(student.getUsername()))
        {
            response.put("message", "Username is already exist!!");
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
        
        if(studentRepository.existsByEmail(student.getEmail()))
        {
            response.put("message", "Email is already exist!!");
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
        
        
        Student stu=new Student();
        stu.setName(student.getName());
        stu.setEmail(student.getEmail());
        stu.setUsername(student.getUsername());
        stu.setPassword(passwordEncoder.encode(student.getPassword()));
        
        Optional<Role> role=roleRepository.findByName("ROLE_STUDENT");
        
        stu.setRoles(Collections.singleton(role.get()));
        
        studentRepository.save(stu);
        response.put("message","User Registered successfully");
        return new ResponseEntity<>(response,HttpStatus.OK);
        
    }
    
    @PostMapping("/signin")
    public ResponseEntity<?> registerStudent(@RequestBody Student student)
    {
        Map<String, String> response=new HashMap<>();
        
        try
        {
            Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(student.getEmail(),student.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
                
            response.put("message", "User signed-in successfully!");
        
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch(Exception e)
        {
            response.put("message", "Problem in Signin!");
            
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
    


}

package com.test.service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.test.model.Role;
import com.test.model.Student;
import com.test.repository.StudentRepository;


@Service
public class StudentDetailsService implements UserDetailsService{
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		
		try {
			
			Student student=studentRepository.findByEmailOrUsername( usernameOrEmail, usernameOrEmail)
					.orElseThrow(() ->new Exception("Username or email not found!!"));
			
		return new User(student.getEmail(),student.getPassword(),mapRolesToAuthorities(student.getRoles()));
		
		} catch (Exception e) {
			
		}
		
		return null;
	}
	
	
	public Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles)
	{
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());	
	}


}

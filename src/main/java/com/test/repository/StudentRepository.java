package com.test.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.test.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	Optional<Student> findByEmail(String email);
	 Optional<Student> findByUsername(String username);
	 Optional<Student> findByEmailOrUsername(String email,String username);
	 
	 Boolean existsByUsername(String username);
	 Boolean existsByEmail(String email);

}

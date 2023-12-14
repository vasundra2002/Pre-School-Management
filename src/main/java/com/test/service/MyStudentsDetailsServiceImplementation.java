package com.test.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.test.model.MyStudent;
import com.test.repository.MyStudentRepository;

@Service
public class MyStudentsDetailsServiceImplementation implements MyStudentsDetailsService {

	@Autowired
	private  MyStudentRepository myStudentRepository;
	
	@Override
	public MyStudent addMyStudent(MyStudent myStudent) {
		
		return myStudentRepository.save(myStudent);
	}

	@Override
	public MyStudent getMyStudentById(long id) {
		// TODO Auto-generated method stub
		return myStudentRepository.findById(id).get();
	}

	@Override
	public List<MyStudent> getAllMyStudents() {
		// TODO Auto-generated method stub
		return myStudentRepository.findAll();
	}

	@Override
	public void deleteMyStudentById(long id) {
		// TODO Auto-generated method stub
		 myStudentRepository.deleteById(id);
	}

	@Override
	public void updateMyStudent(MyStudent myStudent) {
		// TODO Auto-generated method stub
		myStudentRepository.save(myStudent);
	}

	@Override
	public List<MyStudent> findyMyStudentsByName(String name) {
		// TODO Auto-generated method stub
		return myStudentRepository.findByName(name);
	}

	@Override
	public List<MyStudent> findMyStudentsByStandard(String standard) {
		// TODO Auto-generated method stub
		return myStudentRepository.findByStandard(standard);
	}

	@Override
	public MyStudent saveMyStudent(MyStudent myStudent) throws Exception {
		Optional<MyStudent> savedMyStudent = myStudentRepository.findById(myStudent.getId());
        if(savedMyStudent.isPresent()){
            throw new Exception("Student  already exist with given id:" + myStudent.getId());
        }
        return myStudentRepository.save(myStudent);
	}

	
	
	
	

}

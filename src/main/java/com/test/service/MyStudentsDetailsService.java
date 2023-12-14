package com.test.service;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.test.model.MyStudent;

public interface MyStudentsDetailsService {
	public MyStudent addMyStudent(MyStudent myStudent);  //method signatures
	public MyStudent getMyStudentById(long id);

	public List<MyStudent> getAllMyStudents();
    
    public void deleteMyStudentById(long id);
    public void updateMyStudent(MyStudent myStudent);
    public List<MyStudent> findyMyStudentsByName(String name);
    public List<MyStudent> findMyStudentsByStandard(String standard);
    MyStudent saveMyStudent(MyStudent myStudent) throws Exception;

    
    
    
}


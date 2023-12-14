package com.test.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.model.MyStudent;

@Repository
public interface MyStudentRepository  extends JpaRepository<MyStudent, Long> 
{
	//finder method to generate query
    public List<MyStudent> findByName(String name);
    public List<MyStudent> findByStandard(String standard);
}


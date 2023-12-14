package com.test.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@Entity
public class MyStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    @NotEmpty
    @Size(min = 2, message = "Student name should have at least two characters")
    private String name;

    @Column(nullable = false)
    @NotEmpty(message = "Standard cannot be empty!")
    @Size(min = 2, message = "Employee designation should have at least two characters")
    private String standard;

    @Min(value = 3, message = "age must be at least 3!")
    @Max(value = 5, message = "age should not be greater than 5!")
    private double age;

    @NotEmpty(message = "Email can not be empty!")
    @Email(message = "Invalid email-id!")
    private String email;

    @Min(value = 1000000000, message = "PhoneNumber should have at least 10 digits!")
    @Max(value = 9999999999L, message = "PhoneNumber should have at most 10 digits!")
    private long phonenumber;
    
    private String imageName;
    private String url;
    
    @Builder
    public MyStudent(long id, String name, String standard, double age, String email, long phonenumber) {
        this.id = id;
        this.name = name;
        this.standard = standard;
        this.age = age;
        this.email = email;
        this.phonenumber = phonenumber;
    }

    MyStudent(){
    	
    }
    
}

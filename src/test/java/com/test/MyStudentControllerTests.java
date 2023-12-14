package com.test;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.model.MyStudent;
import com.test.service.MyStudentsDetailsService;

public class MyStudentControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MyStudentsDetailsService myStudentsDetailsService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void givenMyStudentObject_whenCreateMyStudent_thenReturnSavedMyStudent() throws Exception {
        // given - precondition or setup
        MyStudent myStudent = MyStudent.builder()
                .name("Kavin")
                .standard("pre.K.G")
                .age(3.0)
                .email("kavin@gmail.com")
                .phonenumber(90876543210L)  // Change the phone number to a long value
                .build();

        given(myStudentsDetailsService.saveMyStudent(myStudent)).willReturn(myStudent);

        // when - action or behavior that we are going to test
        ResultActions response = mockMvc.perform(post("/api/mystudents")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(myStudent)));

        // then - verify the result or output using assert statements
        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(myStudent.getName())))
                .andExpect(jsonPath("$.standard", is(myStudent.getStandard())))
                .andExpect(jsonPath("$.age", is(myStudent.getAge())))
                .andExpect(jsonPath("$.email", is(myStudent.getEmail())))
                .andExpect(jsonPath("$.phonenumber", is(myStudent.getPhonenumber())));
    }

    // Other existing tests...

    // Positive scenario - valid employee id
    // JUnit test for GET employee by id REST API
    @Test
    public void givenMyStudentId_whenGetMyStudentById_thenReturnMyStudentObject() throws Exception {
        // given - precondition or setup
        long myStudentId = 1L;
        MyStudent myStudent = MyStudent.builder().name("Kavin").standard("Fadatare").age(3.0)
                .email("kavin@gmail.com").phonenumber(9087654321L)  // Change the phone number to a long value
                .build();
        given(myStudentsDetailsService.getMyStudentById(myStudentId)).willReturn(myStudent);

        // when - action or the behavior that we are going to test
        ResultActions response = mockMvc.perform(get("/api/employees/{id}", myStudentId));

        // then - verify the output
        response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.name", is(myStudent.getName())))
                .andExpect(jsonPath("$.standard", is(myStudent.getStandard())))
                .andExpect(jsonPath("$.age", is(myStudent.getAge())))
                .andExpect(jsonPath("$.email", is(myStudent.getEmail())))
                .andExpect(jsonPath("$.phonenumber", is(myStudent.getPhonenumber())));
    }

   
}

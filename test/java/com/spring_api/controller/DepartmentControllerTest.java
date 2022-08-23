package com.spring_api.controller;

import com.spring_api.entity.Department;
import com.spring_api.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    private Department department;


    @BeforeEach
    void setUp() {
        department = Department.builder()
                .departmentAddress("Leeds")
                .departmentCode("IT-06")
                .departmentName("IT")
                .departmentId(1L)
                .build();
    }

    @Test
    @Disabled
    void saveDepartment() throws Exception {
        Department inputDepartment = Department.builder()
                .departmentAddress("Leeds")
                .departmentCode("IT-06")
                .departmentName("IT")
                .build();

        Mockito.when(departmentService
                        .saveDepartment(inputDepartment))
                        .thenReturn(department);

        mockMvc.perform(post("/departments")
        .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\"departmentName\":\"IT\",\n" +
                        "\"departmentAddress\":\"Leeds\",\n" +
                        "\"departmentCode\":\"IT-06\"\n" +
                        "}"))
        .andExpect(status().isOk());
    }

    @Test
    @Disabled
    void getDepartmentById() throws Exception {
        Mockito.when(departmentService.getDepartmentById(1L))
                .thenReturn(department);

        mockMvc.perform(get("/departments/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect((status().isOk()))
                .andExpect(jsonPath("$.departmentName").
                        value(department.getDepartmentName()));
    }
}
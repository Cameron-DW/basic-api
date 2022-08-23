package com.spring_api.controller;

import com.spring_api.entity.Department;
import com.spring_api.error.DepartmentNotFoundException;
import com.spring_api.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    private final Logger LOGGER =
            LoggerFactory.getLogger(DepartmentController.class);

    @PostMapping("/departments")
    public Department saveDepartment(@Valid @RequestBody Department department){
        LOGGER.info("Inside saveDepartment() of DepartmentController");
        return departmentService.saveDepartment(department);

    }

    @GetMapping("/departments")
    public List<Department> getDepartments(){
        LOGGER.info("Inside getDepartments() of DepartmentController");
        return departmentService.getDepartments();
    }

    @GetMapping("/departments/{id}")
    public Department getDepartmentById(@PathVariable("id") Long departmentId) throws DepartmentNotFoundException {

        return departmentService.getDepartmentById(departmentId);
    }

    @DeleteMapping("/departments/{id}")
    public String deleteDepartmentById(@PathVariable("id") Long departmentId){
        departmentService.deleteDepartmentById(departmentId);
        return "Department with ID " + departmentId + " has been deleted successfully.";
    }

    @PutMapping("departments/{id}")
    public Department updateDepartmentById(@PathVariable("id") Long departmentId,
                                           @RequestBody Department department){

        return departmentService.updateDepartmentById(departmentId, department);
    }

    @GetMapping("departments/name/{name}")
    public Department getDepartmentByName(@PathVariable("name") String departmentName){

        return departmentService.getDepartmentByName(departmentName);
    }

}

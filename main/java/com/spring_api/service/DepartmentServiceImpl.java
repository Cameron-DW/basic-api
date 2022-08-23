package com.spring_api.service;

import com.spring_api.entity.Department;
import com.spring_api.error.DepartmentNotFoundException;
import com.spring_api.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(Long departmentId) throws DepartmentNotFoundException {

        Optional<Department> department =  departmentRepository.findById(departmentId);

        if (department.isEmpty()){
            throw new DepartmentNotFoundException("Department Not Available");
        }
        return department.get();
    }

    @Override
    public void deleteDepartmentById(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }

    @Override
    public Department updateDepartmentById(Long departmentId, Department department) {
        Department departmentDB =  departmentRepository.findById(departmentId).get();

        if (Objects.nonNull(department.getDepartmentAddress()) &&
                !department.getDepartmentAddress().isEmpty()){
            departmentDB.setDepartmentAddress(department.getDepartmentAddress());
        }

        if (Objects.nonNull(department.getDepartmentCode()) &&
                !department.getDepartmentCode().isEmpty()){
            departmentDB.setDepartmentCode(department.getDepartmentCode());
        }

        if (Objects.nonNull(department.getDepartmentName()) &&
                !department.getDepartmentName().isEmpty()){
            departmentDB.setDepartmentName(department.getDepartmentName());
        }
        departmentRepository.save(departmentDB);
        return departmentDB;
    }

    @Override
    public Department getDepartmentByName(String departmentName) {
        return departmentRepository.findByDepartmentNameIgnoreCase(departmentName);
    }
}

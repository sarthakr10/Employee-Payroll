package com.example.EmployeePayroll.service;

import com.example.EmployeePayroll.dto.EmployeeDTO;
import com.example.EmployeePayroll.model.EmployeeModel;
import com.example.EmployeePayroll.repository.employeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private employeeRepo employeeRepository;

    // Get all employees
    public List<EmployeeModel> getAllUsers() {
        return employeeRepository.findAll();
    }

    // Get employee by ID
    public Optional<EmployeeModel> getUserById(Long id) {
        return employeeRepository.findById(id);
    }

    // Create employee using DTO
    public EmployeeModel createUser(EmployeeDTO employeeDTO) {
        EmployeeModel employee = new EmployeeModel(employeeDTO);
        return employeeRepository.save(employee);
    }

    // Update employee using DTO
    public Optional<EmployeeModel> updateUser(Long id, EmployeeDTO employeeDTO) {
        return employeeRepository.findById(id).map(employee -> {
            employee.setName(employeeDTO.getName());
            employee.setSalary(employeeDTO.getSalary());
            return employeeRepository.save(employee);
        });
    }

    // Delete employee
    public boolean deleteUser(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

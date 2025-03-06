package com.example.EmployeePayroll.service;

import com.example.EmployeePayroll.dto.EmployeeDTO;
import com.example.EmployeePayroll.model.EmployeeModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class EmployeeService {

    private final List<EmployeeModel> employeeList = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1); // Auto-increment ID

    // Get all employees
    public List<EmployeeModel> getAllUsers() {
        return employeeList;
    }

    // Get employee by ID
    public Optional<EmployeeModel> getUserById(Long id) {
        return employeeList.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst();
    }

    // Create employee using DTO
    public EmployeeModel createUser(EmployeeDTO employeeDTO) {
        EmployeeModel employee = new EmployeeModel(idCounter.getAndIncrement(), employeeDTO.getName(), employeeDTO.getSalary());
        employeeList.add(employee);
        return employee;
    }

    // Update employee using DTO
    public Optional<EmployeeModel> updateUser(Long id, EmployeeDTO employeeDTO) {
        return getUserById(id).map(employee -> {
            employee.setName(employeeDTO.getName());
            employee.setSalary(employeeDTO.getSalary());
            return employee;
        });
    }

    // Delete employee
    public boolean deleteUser(Long id) {
        return employeeList.removeIf(employee -> employee.getId().equals(id));
    }
}

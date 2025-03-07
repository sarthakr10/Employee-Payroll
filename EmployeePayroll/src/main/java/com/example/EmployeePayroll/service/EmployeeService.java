package com.example.EmployeePayroll.service;

import com.example.EmployeePayroll.dto.EmployeeDTO;
import com.example.EmployeePayroll.model.EmployeeModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Service
public class EmployeeService {

    private final List<EmployeeModel> employeeList = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1); // Auto-increment ID

    // Get all employees
    public List<EmployeeModel> getAllUsers() {
        log.info("Fetching all employees");
        return employeeList;
    }

    // Get employee by ID
    public Optional<EmployeeModel> getUserById(Long id) {
        log.info("Fetching employee with ID: {}", id);
        return employeeList.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst();
    }

    // Create employee using DTO
    public EmployeeModel createUser(EmployeeDTO employeeDTO) {
        EmployeeModel employee = new EmployeeModel(idCounter.getAndIncrement(), employeeDTO.getName(), employeeDTO.getSalary());
        employeeList.add(employee);
        log.info("Created new employee: {} with ID: {}", employee.getName(), employee.getId());
        return employee;
    }

    // Update employee using DTO
    public Optional<EmployeeModel> updateUser(Long id, EmployeeDTO employeeDTO) {
        log.info("Updating employee with ID: {}", id);
        return getUserById(id).map(employee -> {
            employee.setName(employeeDTO.getName());
            employee.setSalary(employeeDTO.getSalary());
            log.info("Updated employee: {} with new details", employee.getName());
            return employee;
        });
    }

    // Delete employee
    public boolean deleteUser(Long id) {
        log.info("Deleting employee with ID: {}", id);
        return employeeList.removeIf(employee -> employee.getId().equals(id));
    }
}


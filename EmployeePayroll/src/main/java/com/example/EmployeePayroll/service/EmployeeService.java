package com.example.EmployeePayroll.service;

import com.example.EmployeePayroll.dto.EmployeeDTO;
import com.example.EmployeePayroll.exception.EmployeeNotFoundException;
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
    public EmployeeModel getUserById(Long id) {
        log.info("Fetching employee with ID: {}", id);
        return employeeList.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found"));
    }

    // Create employee using DTO
    public EmployeeModel createUser(EmployeeDTO employeeDTO) {
        EmployeeModel employee = new EmployeeModel(idCounter.getAndIncrement(), employeeDTO.getName(), employeeDTO.getSalary());
        employeeList.add(employee);
        log.info("Created new employee: {} with ID: {}", employee.getName(), employee.getId());
        return employee;
    }

    // Update employee using DTO
    public EmployeeModel updateUser(Long id, EmployeeDTO employeeDTO) {
        log.info("Updating employee with ID: {}", id);
        EmployeeModel employee = getUserById(id); // Throws exception if not found
        employee.setName(employeeDTO.getName());
        employee.setSalary(employeeDTO.getSalary());
        log.info("Updated employee: {} with new details", employee.getName());
        return employee;
    }

    // Delete employee
    public void deleteUser(Long id) {
        log.info("Deleting employee with ID: {}", id);
        EmployeeModel employee = getUserById(id); // Throws exception if not found
        employeeList.remove(employee);
        log.info("Employee with ID {} deleted successfully", id);
    }
}

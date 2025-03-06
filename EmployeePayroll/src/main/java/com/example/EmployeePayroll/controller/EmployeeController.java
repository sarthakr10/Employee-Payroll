package com.example.EmployeePayroll.controller;

import com.example.EmployeePayroll.dto.EmployeeDTO;
import com.example.EmployeePayroll.model.EmployeeModel;
import com.example.EmployeePayroll.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employeepayrollservice")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Get all employees
    @GetMapping("/")
    public List<EmployeeModel> getAllUsers() {
        return employeeService.getAllUsers();
    }

    // Get employee by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<EmployeeModel> getUserById(@PathVariable Long id) {
        return employeeService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create employee
    @PostMapping("/create")
    public EmployeeModel createUser(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.createUser(employeeDTO);
    }

    // Update employee
    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeModel> updateUser(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        return employeeService.updateUser(id, employeeDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete employee
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean deleted = employeeService.deleteUser(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}

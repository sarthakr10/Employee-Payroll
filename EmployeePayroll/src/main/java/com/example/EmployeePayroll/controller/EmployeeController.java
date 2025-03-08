package com.example.EmployeePayroll.controller;

import com.example.EmployeePayroll.dto.EmployeeDTO;
import com.example.EmployeePayroll.model.EmployeeModel;
import com.example.EmployeePayroll.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employeepayrollservice")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public ResponseEntity<List<EmployeeModel>> getAllUsers() {
        return ResponseEntity.ok(employeeService.getAllUsers());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<EmployeeModel> getUserById(@PathVariable Long id) {
        EmployeeModel employee = employeeService.getUserById(id); // Throws exception if not found
        return ResponseEntity.ok(employee);
    }

    @PostMapping("/create")
    public ResponseEntity<EmployeeModel> createUser(@Valid @RequestBody EmployeeDTO employeeDTO) {
        EmployeeModel employee = employeeService.createUser(employeeDTO);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeModel> updateUser(@PathVariable Long id, @Valid @RequestBody EmployeeDTO employeeDTO) {
        EmployeeModel updatedEmployee = employeeService.updateUser(id, employeeDTO); // Throws exception if not found
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        employeeService.deleteUser(id); // Throws exception if not found
        return ResponseEntity.ok("Employee deleted successfully");
    }
}

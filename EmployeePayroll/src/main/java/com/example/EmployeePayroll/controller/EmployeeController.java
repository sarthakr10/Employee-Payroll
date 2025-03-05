package com.example.EmployeePayroll.controller;
<<<<<<< HEAD

package com.example.EmployeePayroll.Controller;
=======
>>>>>>> UC2
import com.example.EmployeePayroll.model.EmployeeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
<<<<<<< HEAD
import com.example.EmployeePayroll.Repository.employeeRepo;
=======
import com.example.EmployeePayroll.repository.employeeRepo;
>>>>>>> UC2
import java.util.List;

import java.util.Optional;
@RestController
<<<<<<< HEAD
@RequestMapping("/employee")
=======
@RequestMapping("/employeepayrollservice")
>>>>>>> UC2
public class EmployeeController {
    @Autowired
    employeeRepo employeeRepository;

<<<<<<< HEAD

    //http:localhost:8080/employee/findall
    @GetMapping("/findall")
=======
    //localhost:8080/employeepayrollservice/

    @GetMapping("/")
>>>>>>> UC2
    public List<EmployeeModel> getAllUsers() {
        return employeeRepository.findAll();
    }

<<<<<<< HEAD
    //http:localhost:8080/employee/findallgetbyid/1
    @GetMapping("getbyid/{id}")
=======
    //localhost:8080/employeepayrollservice

    @GetMapping("/get/{id}")
>>>>>>> UC2
    public ResponseEntity<EmployeeModel> getUserById(@PathVariable Long id) {
        return employeeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
<<<<<<< HEAD

    //http:localhost:8080/employee/create/post
    @PostMapping("create/post")
=======
    //http://localhost:8080/employeepayrollservice/create
    @PostMapping("create")
>>>>>>> UC2
    public EmployeeModel createUser(@RequestBody EmployeeModel employee) {
        return employeeRepository.save(employee);
    }

<<<<<<< HEAD
    //http:localhost:8080/employee/update/1
=======

    //http://localhost:8080/employeepayrollservice/update
>>>>>>> UC2
    @PutMapping("update/{id}")
    public ResponseEntity<EmployeeModel> updateUser(@PathVariable Long id, @RequestBody EmployeeModel userDetails) {
        Optional<EmployeeModel> optionalUser = employeeRepository.findById(id);

        if (optionalUser.isPresent()) {
            EmployeeModel user = optionalUser.get();
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            return ResponseEntity.ok(employeeRepository.save(user));
        }
        return ResponseEntity.notFound().build();
    }

    //http:localhost:8080/employee/delete/1
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
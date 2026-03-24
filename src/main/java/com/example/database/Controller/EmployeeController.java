package com.example.database.Controller;

import com.example.database.Entity.User;
import com.example.database.Service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @PostMapping("/addEmployee")
    public ResponseEntity<User> addEmployee(@RequestBody User employee){
        return ResponseEntity.ok(employeeService.addEmployee(employee));
    }
}

package com.example.database.Service;

import com.example.database.Entity.Employee;
import com.example.database.Repository.EmployeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
}

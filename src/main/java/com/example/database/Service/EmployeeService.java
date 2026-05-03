package com.example.database.Service;

import com.example.database.Entity.User;
import com.example.database.Repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }
    public User addEmployee(User employee) {
        return employeeRepository.save(employee);
    }
}

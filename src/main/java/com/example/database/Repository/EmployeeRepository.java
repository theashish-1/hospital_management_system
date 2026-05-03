package com.example.database.Repository;

import com.example.database.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<User,Long>{
}

package com.example.employeebonusproject.repository;

import com.example.employeebonusproject.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}



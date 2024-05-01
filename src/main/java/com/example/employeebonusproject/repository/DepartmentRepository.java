package com.example.employeebonusproject.repository;

import com.example.employeebonusproject.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findByName(String department);
}
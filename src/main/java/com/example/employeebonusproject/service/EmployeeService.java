package com.example.employeebonusproject.service;

import com.example.employeebonusproject.model.BonusEmployee;
import com.example.employeebonusproject.model.BonusResponse;
import com.example.employeebonusproject.model.Employee;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

// EmployeeService.java
public interface EmployeeService {
    void storeEmployees(List<BonusEmployee> employees) throws ParseException;
    BonusResponse getEligibleEmployees(Date date);
}

package com.example.employeebonusproject.controller;

import com.example.employeebonusproject.model.BonusEmployee;
import com.example.employeebonusproject.model.BonusResponse;
import com.example.employeebonusproject.model.Employee;
import com.example.employeebonusproject.model.EmployeeWrapper;
import com.example.employeebonusproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

// EmployeeController.java
@RestController
@RequestMapping("/tci/employee-bonus")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/")
    public ResponseEntity<String> storeEmployees(@RequestBody EmployeeWrapper employeeWrapper) {
        try {
            List<BonusEmployee> employees = employeeWrapper.getEmployees();
            employeeService.storeEmployees(employees);
            return ResponseEntity.ok("Employees stored successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error storing employees: " + e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<BonusResponse> getEligibleEmployees(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") String dateString) {
        dateString = dateString.replace("\"", "");

        SimpleDateFormat formatter = new SimpleDateFormat("MMM-dd-yyyy", Locale.ENGLISH);
        Date date;
        try {
            date = formatter.parse(dateString);
        } catch (ParseException e) {
            // Handle parsing error
            return ResponseEntity.badRequest().body(null); // Return bad request status
        }

        try {
            BonusResponse response = employeeService.getEligibleEmployees(date);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            BonusResponse response = new BonusResponse();
            response.setErrorMessage("Error retrieving eligible employees: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}

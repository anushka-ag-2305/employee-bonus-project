package com.example.employeebonusproject.controller;

import com.example.employeebonusproject.model.BonusEmployee;
import com.example.employeebonusproject.model.BonusResponse;
import com.example.employeebonusproject.model.EmployeeWrapper;
import com.example.employeebonusproject.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void storeEmployees() throws ParseException {
        List<BonusEmployee> bonusEmployees = Collections.singletonList(
                new BonusEmployee("John Doe", "Finance", 5000, "USD", "Jan-01-2022", "null")
        );

        ResponseEntity<String> response = employeeController.storeEmployees(new EmployeeWrapper(bonusEmployees));

        assertEquals("Employees stored successfully", response.getBody());
        verify(employeeService, times(1)).storeEmployees(bonusEmployees);
    }

    @Test
    void getEligibleEmployees_withInvalidDateFormat() {
        ResponseEntity<BonusResponse> response = employeeController.getEligibleEmployees("invalid-date");

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
        verify(employeeService, never()).getEligibleEmployees(any());
    }

    @Test
    void getEligibleEmployees_withInvalidDate() {
        ResponseEntity<BonusResponse> response = employeeController.getEligibleEmployees("2022-30-01");

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
        verify(employeeService, never()).getEligibleEmployees(any());
    }
}

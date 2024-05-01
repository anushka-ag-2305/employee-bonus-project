package com.example.employeebonusproject.service;

import com.example.employeebonusproject.model.BonusEmployee;
import com.example.employeebonusproject.model.BonusResponse;
import com.example.employeebonusproject.model.Employee;
import com.example.employeebonusproject.repository.DepartmentRepository;
import com.example.employeebonusproject.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void storeEmployees() throws ParseException {
        List<BonusEmployee> bonusEmployees = new ArrayList<>();
        BonusEmployee employee1 = new BonusEmployee("John Doe", "Finance", 5000, "USD", "Jan-01-2022", "Feb-15-2022");
        BonusEmployee employee2 = new BonusEmployee("Jane Smith", "HR", 4000, "EUR", "Feb-15-2022", "Mar-20-2023");
        bonusEmployees.add(employee1);
        bonusEmployees.add(employee2);

        employeeService.storeEmployees(bonusEmployees);

        verify(employeeRepository, times(2)).save(any(Employee.class));
    }

    @Test
    void getEligibleEmployees() throws ParseException {
        List<Employee> employees = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("MMM-dd-yyyy");
        Date date = formatter.parse("Apr-01-2022");
        Employee employee1 = new Employee("John Doe", "Finance", 5000, "USD", formatter.parse("Jan-01-2022"), null);
        Employee employee2 = new Employee("Jane Smith", "HR", 4000, "EUR", formatter.parse("Feb-15-2022"), formatter.parse("Mar-20-2023"));
        employees.add(employee1);
        employees.add(employee2);

        when(employeeRepository.findAll()).thenReturn(employees);

        BonusResponse response = employeeService.getEligibleEmployees(date);

        assertEquals(2, response.getData().size());
    }

    @Test
    void isEmployeeEligible() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("MMM-dd-yyyy");
        Date date = formatter.parse("Apr-01-2022");
        Employee employee1 = new Employee("John Doe", "Finance", 5000, "USD", formatter.parse("Jan-01-2022"), null);
        boolean result1 = employeeService.isEmployeeEligible(employee1, date);
        assertTrue(result1);

    }
}

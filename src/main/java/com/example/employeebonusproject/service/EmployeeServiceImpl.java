package com.example.employeebonusproject.service;

import com.example.employeebonusproject.model.BonusEmployee;
import com.example.employeebonusproject.model.BonusResponse;
import com.example.employeebonusproject.model.Department;
import com.example.employeebonusproject.model.Employee;
import com.example.employeebonusproject.repository.DepartmentRepository;
import com.example.employeebonusproject.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;


    public void storeEmployees(List<BonusEmployee> employees) throws ParseException {
        for (BonusEmployee employee : employees) {
            Employee entity = new Employee();
            entity.setEmpName(employee.getEmpName());
            entity.setDepartment(employee.getDepartment());
            entity.setAmount(employee.getAmount());
            entity.setCurrency(employee.getCurrency());

            SimpleDateFormat formatter = new SimpleDateFormat("MMM-dd-yyyy", Locale.ENGLISH);
            Date joiningDate = formatter.parse(employee.getJoiningDate());
            Date exitDate = formatter.parse(employee.getExitDate());


            entity.setJoiningDate(joiningDate);
            entity.setExitDate(exitDate);

            employeeRepository.save(entity);
        }
    }

    @Override
    public BonusResponse getEligibleEmployees(Date date) {
        BonusResponse response = new BonusResponse();
        List<Map<String, Object>> data = new ArrayList<>();

        try {
            List<Employee> eligibleEmployees = employeeRepository.findAll().stream()
                    .filter(emp -> isEmployeeEligible(emp, date))
                    .sorted((emp1, emp2) -> emp1.getEmpName().compareToIgnoreCase(emp2.getEmpName()))
                    .collect(Collectors.toList());

            Map<String, List<Map<String, Object>>> employeesByCurrency = eligibleEmployees.stream()
                    .collect(Collectors.groupingBy(Employee::getCurrency,
                            Collectors.mapping(emp -> {
                                Map<String, Object> employeeMap = new LinkedHashMap<>();
                                employeeMap.put("empName", emp.getEmpName());
                                employeeMap.put("amount", emp.getAmount());
                                return employeeMap;
                            }, Collectors.toList())));

            employeesByCurrency.forEach((currency, employees) -> {
                Map<String, Object> currencyData = new LinkedHashMap<>();
                currencyData.put("currency", currency);
                currencyData.put("employees", employees);
                data.add(currencyData);
            });

            response.setData(data);
        } catch (Exception e) {
            response.setErrorMessage("Error retrieving eligible employees: " + e.getMessage());
        }

        return response;
    }


    boolean isEmployeeEligible(Employee employee, Date date) {
        Date joiningDate = employee.getJoiningDate();
        Date exitDate = employee.getExitDate();

        return (joiningDate.before(date) || joiningDate.equals(date)) && (exitDate == null || exitDate.after(date));
    }

}

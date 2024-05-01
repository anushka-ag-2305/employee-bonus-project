package com.example.employeebonusproject.model;

import com.example.employeebonusproject.model.BonusEmployee;
import java.util.List;

public class EmployeeWrapper {
    private List<BonusEmployee> employees;

    public EmployeeWrapper(List<BonusEmployee> bonusEmployees) {
        this.employees=bonusEmployees;
    }
    public EmployeeWrapper(){

    };

    public List<BonusEmployee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<BonusEmployee> employees) {
        this.employees = employees;
    }
}

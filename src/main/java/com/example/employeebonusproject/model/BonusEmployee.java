package com.example.employeebonusproject.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class BonusEmployee {
    private String empName;
    private String department;
    private int amount;
    private String currency;
    private String joiningDate;
    private String exitDate;

    // Custom date format
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("[MMM-dd-yyyy][MM-dd-yyyy][yyyy-MM-dd]").withLocale(Locale.ENGLISH);

    // Constructors, getters, and setters

    public BonusEmployee(String empName, String department, int amount, String currency, String joiningDate, String exitDate) {
        this.empName = empName;
        this.department = department;
        this.amount = amount;
        this.currency = currency;
        this.joiningDate = joiningDate;
        this.exitDate = exitDate;
    }

    public BonusEmployee() {

    }

    public String getEmpName() {
            return empName;
        }

        public void setEmpName(String empName) {
            this.empName = empName;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getJoiningDate() {
            return joiningDate;
        }

        public void setJoiningDate(String joiningDate) {
            this.joiningDate = joiningDate;
        }

        public String getExitDate() {
            return exitDate;
        }

        public void setExitDate(String exitDate) {
            this.exitDate = exitDate;
        }
    }



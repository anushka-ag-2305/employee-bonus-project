package com.example.employeebonusproject.model;

import java.util.List;
import java.util.Map;

public class BonusResponse {
    private String errorMessage="";
    private List<Map<String, Object>> data;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<Map<String, Object>> getData() {
        return data;
    }

    public void setData(List<Map<String, Object>> data) {
        this.data = data;
    }
}

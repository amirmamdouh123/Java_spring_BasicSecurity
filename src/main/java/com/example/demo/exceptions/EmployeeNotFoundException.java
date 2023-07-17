package com.example.demo.exceptions;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Long id) {
        super("Employee with Id: " + id + " is not found.");
    }
}

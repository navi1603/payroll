package com.example.payroll.exeption;

public class EmployeeNotFoundExeption extends RuntimeException {

    public EmployeeNotFoundExeption(Long id) {
        super("Could not find employee " + id);
    }
}

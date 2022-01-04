package com.example.payroll.controller;

import com.example.payroll.entity.Employee;
import com.example.payroll.exeption.EmployeeNotFoundExeption;
import com.example.payroll.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeContoller {

    private  final EmployeeRepository repository;

    public EmployeeContoller(EmployeeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/employees")
    public List<Employee> getAll (){
        return repository.findAll();
    }

    @GetMapping("/employees/{id}")
    public Employee getById (@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundExeption(id));

    }

    @PostMapping("/employees")
    public void newEmployee(@RequestBody Employee newEmployee){
        repository.save(newEmployee);
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable Long id){
        repository.deleteById(id);
    }

    @PutMapping("/employees/{id}")
    public Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id){
        return repository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return repository.save(newEmployee);
                });

    }

}

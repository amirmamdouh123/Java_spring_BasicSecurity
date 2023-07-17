package com.example.demo.services;

import com.example.demo.entities.Employee;
import com.example.demo.exceptions.*;
import com.example.demo.repos.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepo employeeRepo;

    public List<Employee> getAllEmployees(){
        return employeeRepo.findAll();
    }

    public Employee getEmployeeById(Long id){
        Optional<Employee> employee = employeeRepo.findById(id);
        return employee.orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    public Long createEmployee(Employee employee){
        setNewEmpVacationBalance(employee);
        return employeeRepo.save(employee).getId();
    }

    private void setNewEmpVacationBalance(Employee employee) {
        employee.setVacationBalance(21);
    }

    public void updateEmployee(Long id, Employee employee){
        if(id == null) throw new MissingDataException("Employee id is missing");
        employeeRepo.save(employee);
    }

    public void deleteEmployee(Long id){
        if(id == null) throw new MissingDataException("Employee id is missing");
        employeeRepo.deleteById(id);
    }

}

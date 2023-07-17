package com.example.demo.controllers;

import com.example.demo.DTOs.EmployeeVacationRequest;
import com.example.demo.entities.Employee;
import com.example.demo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<?> getAllEmployees(){
        try{
            return ResponseEntity.ok(employeeService.getAllEmployees());
        }
        catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id){
        try{
            return ResponseEntity.ok(employeeService.getEmployeeById(id));
        }
        catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee){
        try{
            return new ResponseEntity<>(employeeService.createEmployee(employee), HttpStatus.CREATED);
        }
        catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody Employee employee){
        try{
            employeeService.updateEmployee(id, employee);
            return ResponseEntity.ok().build();
        }
        catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id){
        try{
            employeeService.deleteEmployee(id);
            return ResponseEntity.ok().build();
        }
        catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

}

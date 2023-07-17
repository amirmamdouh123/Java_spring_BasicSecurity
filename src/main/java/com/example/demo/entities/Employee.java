package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "employees", schema = "HR")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee{

    @Id
    @Column(name = "EMPLOYEE_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMP_SEQ")
    @SequenceGenerator(name = "EMP_SEQ", sequenceName = "HR.EMP_SEQ", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "DEPARTMENT_ID")
    private Department department;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "HIRE_DATE")
    private LocalDate hireDate;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "SALARY")
    private Float salary;

    @Column(name = "VACATION_BALANCE", updatable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer vacationBalance;

}

package com.example.demo.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DEPARTMENTS", schema = "HR")
@Data
public class Department {

    @Id
    @Column(name = "DEPARTMENT_ID")
    private Long id;

    @Column(name = "DEPARTMENT_NAME")
    private String name;

}

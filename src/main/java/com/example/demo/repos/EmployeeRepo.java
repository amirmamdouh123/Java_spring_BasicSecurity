package com.example.demo.repos;

import com.example.demo.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Date;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    @Modifying
    @Query("update Employee e set e.vacationBalance = :newVacationBalance where e.id = :id")
    void updateVacationBalance(Long id, Integer newVacationBalance);

}

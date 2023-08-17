package com.example.demo.jwt_security.repos;

import com.example.demo.jwt_security.entities.AppUser;
import com.example.demo.jwt_security.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface roleRepo extends JpaRepository<Role,Long> {


    @Query("select e from Role e where e.role =:role")
    public Optional<Role> getByName(@Param("role")String role);


}

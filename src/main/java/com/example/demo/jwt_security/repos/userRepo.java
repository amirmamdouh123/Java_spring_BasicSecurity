package com.example.demo.jwt_security.repos;

import com.example.demo.jwt_security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface userRepo extends JpaRepository<AppUser,Long> {

    @Query("select e from AppUser e where e.username =:name")
    public Optional<AppUser> getByName(String name);

}

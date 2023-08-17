package com.example.demo.jwt_security.controllers;

import com.example.demo.jwt_security.entities.AppUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("secure")
public class secure_controller {


    @GetMapping("getAuth")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAuthUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(auth.getPrincipal());
    }

    @GetMapping("username")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> dodo(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(((AppUser)auth.getPrincipal()).getUsername());
    }

    @GetMapping("roles")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getRoles(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(((AppUser)auth.getPrincipal()).getAuthorities());
    }


}

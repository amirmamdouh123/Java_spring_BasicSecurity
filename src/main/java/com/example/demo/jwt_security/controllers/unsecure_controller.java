package com.example.demo.jwt_security.controllers;

import com.example.demo.jwt_security.entities.AppUser;
import com.example.demo.jwt_security.services.UserService;
import com.example.demo.jwt_security.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import com.example.demo.jwt_security.user_dto.*;
@Slf4j
@RestController
@RequestMapping("unsecure")
public class unsecure_controller {
    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager; //that will automatically validate username and password
    @Autowired
    JwtService jwtService;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody UserLoginDto user){
        UsernamePasswordAuthenticationToken auth= new UsernamePasswordAuthenticationToken(
                user.getUsername(),
                user.getPassword()
        );
        authenticationManager.authenticate(auth);     //automatically make validation on username and password , if failed will throw exception
        //if passed the previous line then it means that it validated successfully
        //then we need to create token now
        AppUser appUser = (AppUser) userService.loadUserByUsername(user.getUsername());

        String token =jwtService.generate_token(appUser);
        return ResponseEntity.ok(new UserLoginResponse(user.getUsername(),token));
    }


}

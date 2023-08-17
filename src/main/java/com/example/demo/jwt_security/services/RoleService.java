package com.example.demo.jwt_security.services;

import com.example.demo.jwt_security.entities.AppUser;
import com.example.demo.jwt_security.entities.Role;
import com.example.demo.jwt_security.repos.roleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    roleRepo roleRepo;
    public void addRole(Role role){
        roleRepo.save(role);
    }

    public List<Role> getAllRoles(){
        return roleRepo.findAll();
    }


    public Role getByName(String role){
        Optional<Role> r= roleRepo.getByName(role);
         
        if(r.isPresent()){
            return r.get();
        }
        throw new UsernameNotFoundException("role is not found");
    }
}

package com.example.demo.commands;

import com.example.demo.jwt_security.entities.AppUser;
import com.example.demo.jwt_security.entities.Role;
import com.example.demo.jwt_security.enums.roleEnums;
import com.example.demo.jwt_security.services.*;
//import com.sun.tools.javac.main.CommandLine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class command implements CommandLineRunner {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    @Override
    @Transactional()
    public void run(String... args) throws Exception {
        // Check if roles exist in the database and create them if not
        if (roleService.getAllRoles().isEmpty()) {
            roleService.addRole(new Role(null, roleEnums.ROLE_ADMIN));
            roleService.addRole(new Role(null, roleEnums.ROLE_GUEST));
            roleService.addRole(new Role(null, roleEnums.ROLE_CUSTOMER));
        }

        // Retrieve the roles from the database
        Role adminRole = roleService.getByName("ROLE_ADMIN");
        Role userRole = roleService.getByName("ROLE_GUEST");
        Role empRole = roleService.getByName("ROLE_CUSTOMER");

        // Create sets of roles for the users
        List<Role> adminRoles = new ArrayList<>();
        List<Role> usersRoles = new ArrayList<>();
        List<Role> empRoles = new ArrayList<>();

        adminRoles.add(adminRole);
        usersRoles.add(userRole);
        empRoles.add(empRole);

        // Check if users exist in the database and create them if not
        if (userService.getAllUsers().isEmpty()) {
            // Create the admin user
            AppUser adminUser = new AppUser(null, "amirAdmin", "1234", adminRoles);
            userService.addAppUser(adminUser);

            // Create the employee user
            AppUser empUser = new AppUser(null, "miroGUEST", "1234", empRoles);
            userService.addAppUser(empUser);

            // Create the regular user
            AppUser regUser = new AppUser(null, "mrmrCUSTOMER", "1234", usersRoles);
            userService.addAppUser(regUser);
        }
    }
}


//    }
//}

package com.example.demo.jwt_security.services;

import com.example.demo.jwt_security.WebSecurityConfig;
import com.example.demo.jwt_security.entities.AppUser;
import com.example.demo.jwt_security.repos.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    public userRepo userRepo;

    @Autowired
    PasswordEncoder s =WebSecurityConfig.passwordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> a = userRepo.getByName(username);
        if(a == null){
            throw new UsernameNotFoundException("not found user");
        }
        return a.get();
    }

//    public AppUser getByName(String username){
//        Optional<AppUser> a = userRepo.getByName(username);
//        if(a == null){
//            throw new UsernameNotFoundException("not found user");
//        }
//       return a.get();
//    }


public void addAppUser(AppUser appUser){

        appUser.setPassword(s.encode(appUser.getPassword()));
        userRepo.save(appUser);
}

    public List<AppUser> getAllUsers(){
        return userRepo.findAll();
    }


}

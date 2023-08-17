package com.example.demo.jwt_security.entities;

import com.example.demo.jwt_security.enums.roleEnums;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name="sec_roles", schema = "HR")

//@Builder
@JsonIgnoreProperties("users")
@NoArgsConstructor
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMP_SEQ")
    @SequenceGenerator(name = "EMP_SEQ", sequenceName = "HR.EMP_SEQ", allocationSize = 1)
    Long id;

    @Column(name="rolee")
    String role;


    public String getRole() {
        return role.toString();
    }

    public void setRole(roleEnums role) {
        this.role = role.toString();
    }

    @ManyToMany(mappedBy = "roles" ,cascade = CascadeType.ALL)
    Set<AppUser> users;


    public Role(Long id,roleEnums role) {
    this.id=id;
    this.role=role.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<AppUser> getUsers() {
        return users;
    }

    public void setUsers(Set<AppUser> users) {
        this.users = users;
    }


    //    public void addUser(AppUser user){
//        users.add(user);
//    }
//    public void removeUser(AppUser user){
//        users.remove(user);
//    }

    @Override
    public String toString() {
        return "id: "+getId() +" Name: "+ getRole();
    }

    @Override
    public String getAuthority() {
        return role.toString();
    }
}
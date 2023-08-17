package com.example.demo.jwt_security.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "SEC_USERS",schema = "HR")
@NoArgsConstructor
@Setter
@AllArgsConstructor
@Builder
@JsonIgnoreProperties("password")
public class AppUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMP_SEQ")
    @SequenceGenerator(name = "EMP_SEQ", sequenceName = "HR.EMP_SEQ", allocationSize = 1)
    Long id;

    public String username;

    public String password;

    @ManyToMany
    @JoinTable(schema = "HR",name="RELATION_USERS_ROLES",
            joinColumns =  @JoinColumn(name="USER_ID",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="ROLE_ID",referencedColumnName = "id"))

    public List<Role> roles;




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

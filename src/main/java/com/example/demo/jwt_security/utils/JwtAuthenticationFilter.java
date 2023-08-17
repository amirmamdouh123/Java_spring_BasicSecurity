package com.example.demo.jwt_security.utils;

import com.example.demo.jwt_security.entities.AppUser;
import com.example.demo.jwt_security.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;
@Component
@RequiredArgsConstructor        //makes constructor for all final values
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    JwtService jwtService;
    @Autowired
    UserService userService;

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        log.info("goaaaaaaaaaaaaaaaaa");
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            log.info("filtered");

            return;
        }
        log.info("not filtered");

        jwt =authHeader.substring(7); //token
        String jwtUsername= jwtService.extractUsername(jwt);
        AppUser appUser = (AppUser)this.userService.loadUserByUsername(jwtUsername);
        boolean isValid= jwtService.isValidToken(jwt,appUser);
        if(isValid){
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                appUser,
                    null,
                    appUser.getAuthorities()
            );
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
        filterChain.doFilter(request,response);
    }

}

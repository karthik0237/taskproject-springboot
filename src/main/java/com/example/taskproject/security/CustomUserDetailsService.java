package com.example.taskproject.security;

import com.example.taskproject.entity.User;
import com.example.taskproject.exception.UserNotFound;
import com.example.taskproject.repository.UserRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import java.util.*;
import java.util.stream.Collectors;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
         User user = userRepository.findByEmail(email)
                 .orElseThrow(
                 () -> new UserNotFound(String.format("User with email '%s' is not found",email))
         );

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),user.getPassword(),getAuthorities());
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<String> roles = new HashSet<String>();
        roles.add("ROLE_ADMIN");
        return  roles.stream().map(
                role -> new SimpleGrantedAuthority(role)
        ).collect(Collectors.toList());
    }
}


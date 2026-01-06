package com.example.taskproject.security;

import com.example.taskproject.entity.User;

import com.example.taskproject.exception.UserNotFound;
import com.example.taskproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

// create custom userdetailsService which interacts with Database for fetching user using userRepo,
// UserDetailsService is an Interface which contains loadByUsername which gets user from database by using parameter like username/email

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new UserNotFound(String.format("User with email: %s doesn't exist", email))
        );
        Set<String> roles = new HashSet<String>();
        roles.add("ROLE_ADMIN");
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(),userAuthorities(roles));
    }

    private Collection<? extends GrantedAuthority> userAuthorities(Set<String> roles){
        return roles.stream().map(
                (role) -> new SimpleGrantedAuthority(role)
        ).collect(Collectors.toList());
    }
}

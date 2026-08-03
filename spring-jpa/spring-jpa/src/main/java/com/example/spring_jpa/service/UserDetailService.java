package com.example.spring_jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.spring_jpa.model.User;
import com.example.spring_jpa.repository.UserRepository;

@Component
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));

       Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map((role) -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());
       return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
       );
    }

    // public String createUser(String username, String password) {
    //     com.example.spring_jpa.model.User user = (com.example.spring_jpa.model.User) org.springframework.security.core.userdetails.User.builder()
    //             .username(username)
    //             .password(new BCryptPasswordEncoder().encode(password))
    //             .roles("USER")
    //             .build();

    //     userRepository.save(user);
    //     return "User created successfully";
    // }
}

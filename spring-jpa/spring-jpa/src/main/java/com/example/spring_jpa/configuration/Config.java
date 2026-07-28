package com.example.spring_jpa.configuration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.context.annotation.Bean;

@Configuration
public class Config {
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
        .username("user")
        .password("{noop}password")
        .roles("USER")
        .build();

        UserDetails admin = User.builder()
		.username("admin")
		.password("{noop}passwordAdmin")
		.roles("USER", "ADMIN")
		.build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}

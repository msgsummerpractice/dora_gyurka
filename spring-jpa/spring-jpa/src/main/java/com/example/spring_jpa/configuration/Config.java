package com.example.spring_jpa.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authorization.EnableMultiFactorAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.FactorGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

import com.example.spring_jpa.filter.JwtAuthenticationFilter;
import com.example.spring_jpa.providers.JWTAuthenticationEntryPoint;


import org.springframework.context.annotation.Bean;

@EnableMultiFactorAuthentication(authorities = {FactorGrantedAuthority.PASSWORD_AUTHORITY, FactorGrantedAuthority.OTT_AUTHORITY})
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class Config {

    // @Autowired
    // private UserDetailsService userDetailsService;

    private final UserDetailsService userDetailsService;

    private final JWTAuthenticationEntryPoint authenticationEntryPoint;

    private final JwtAuthenticationFilter authenticationFilter;

    public Config(UserDetailsService userDetailsService, JWTAuthenticationEntryPoint authenticationEntryPoint, JwtAuthenticationFilter authenticationFilter) {
        this.userDetailsService = userDetailsService;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.authenticationFilter = authenticationFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // http
        // .csrf(csrf -> csrf.disable())
        //         .cors(cors -> cors.disable())
        //         .authorizeHttpRequests(req -> req
        //                 .requestMatchers(HttpMethod.DELETE, "/api/users/delete/{id}").hasRole("ADMIN")
        //                 .anyRequest().authenticated()
        //         )
        //         .formLogin((form) -> form
        //                 .loginPage("/login.html")
        //                 .loginProcessingUrl("/api/users/login")
        //                 .defaultSuccessUrl("/api/users")
        //                 .permitAll()
        //         ).httpBasic(Customizer.withDefaults());
        // return http.build();

        http.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .sessionManagement(session -> 
                     session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests((auth) -> {
                        auth.requestMatchers("/api/auth/**").permitAll();
                        auth.anyRequest().authenticated();
                });
        http.exceptionHandling(ex -> ex
                        .authenticationEntryPoint(authenticationEntryPoint));

        http.addFilterBefore(authenticationFilter, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean 
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}
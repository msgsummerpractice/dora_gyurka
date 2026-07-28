package com.example;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackages = "com.example")
public class AppConfig {

    @Bean
    public Company company() {
        return new Company();
    } 


    @Bean
    public Menthor menthor() {
        return new Menthor();
    }

    @Bean
    public Intern intern() {
        return new Intern();
    }
    
}

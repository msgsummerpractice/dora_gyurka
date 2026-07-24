package com.example.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring")
public class ConfigProperties {

    @Value("${app.server.port}")
    private String port;


    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
} 

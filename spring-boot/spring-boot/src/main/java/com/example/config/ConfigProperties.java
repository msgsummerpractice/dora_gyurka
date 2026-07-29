package com.example.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "server")
public class ConfigProperties {

    private String port;

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}

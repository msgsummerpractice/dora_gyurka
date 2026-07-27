package com.example.controller;

import org.springframework.web.bind.annotation.RestController;
import com.example.config.ConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import com.example.model.User;
import com.example.service.UserService;

import jakarta.validation.constraints.Size;

import org.springframework.web.bind.annotation.PathVariable;

import org.slf4j.*;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ConfigProperties configProperties;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Value("${spring.application.name}")
    private String appName;

    @GetMapping("/app-name")
    public ResponseEntity<String> getAppName() {
        return ResponseEntity.ok(appName);
    }

    @GetMapping
    public List<User> getAllUsers() {
        logger.info("Fetching all users");
        return userService.getAllUsers();
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<String> getUserByEmail(@Size(max = 20, message = "Email must be have maximum 20 characters") @PathVariable("email") String email) {
        logger.info("Fetching user by email: {}", email);

        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @GetMapping("/config")
    public String getConfigProperties() {
        logger.info("Fetching configuration properties");
        return "Server Port: " + configProperties.getPort();
    }
}

package com.example.controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import com.example.model.User;
import com.example.service.UserService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

   // @Autowired
   // private UserService userService;
    
    @GetMapping("/all")
    public List <User> getAllUsers() {
        return List.of(new User("John Doe", "john.doe@example.com"), new User("Jane Doe", "jane.doe@example.com"));
    }
}

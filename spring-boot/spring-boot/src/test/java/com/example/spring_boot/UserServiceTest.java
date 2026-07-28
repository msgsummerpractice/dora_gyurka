package com.example.spring_boot;


import com.example.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock 
    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    public void setUp() {
        userService = new UserService(userRepository);
    }

    @Test
    void testUserCanApplyToInternship() {

        Assertions.assertFalse(userService.canApplyToInternship(10));
        Assertions.assertTrue(userService.canApplyToInternship(20));
    }
    
}

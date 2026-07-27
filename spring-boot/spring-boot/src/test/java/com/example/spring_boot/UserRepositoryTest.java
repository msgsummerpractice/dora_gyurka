package com.example.spring_boot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import com.example.model.User;
import com.example.repository.UserRepository;
import org.junit.jupiter.api.Assertions;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {

    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepository();
    }

    @Test
    void testGetAllUsers() {
        
        List<User> users = userRepository.getAllUsers();

        Assertions.assertEquals(2, users.size());
    }

}

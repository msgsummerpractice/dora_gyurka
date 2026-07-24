package com.example.spring_boot;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import java.util.List;
import com.example.model.User;
import com.example.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;


    @Test
    void testGetAllUsers() {
        when(userRepository.getAllUsers()).thenReturn(List.of(
            new com.example.model.User("John Doe", "john.doe@example.com"),
            new com.example.model.User("Jane Doe", "jane.doe@example.com")
        ));

        List<User> users = userRepository.getAllUsers();

        assert users.size() == 2;
    }
    
}

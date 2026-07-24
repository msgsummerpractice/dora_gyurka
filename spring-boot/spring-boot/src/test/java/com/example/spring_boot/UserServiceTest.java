package com.example.spring_boot;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import com.example.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserService userService;


    @Test
    void testGetAllUsers() {
        when(userService.canApplyToInternship(10)).thenReturn(false);
        when(userService.canApplyToInternship(20)).thenReturn(true);

        assert !userService.canApplyToInternship(10);
        assert userService.canApplyToInternship(20);
    }
    
}

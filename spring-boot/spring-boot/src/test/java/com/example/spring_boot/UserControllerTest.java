package com.example.spring_boot;

import com.example.config.ConfigProperties;
import com.example.controller.UserController;
import com.example.service.UserService;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {
    
     @Autowired
    private MockMvc mockMvc;

     @MockitoBean
    private UserService userService;

    @MockitoBean
    private ConfigProperties configProperties;

     @Test
    public void testEndpointGetUserByEmail() throws Exception {
        String email = "john@email.com";
        when(userService.getUserByEmail(email)).thenReturn("John Doe");
        this.mockMvc.perform(MockMvcRequestBuilders.get("/users/email/{email}", email))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("John Doe"))
                .andDo(MockMvcResultHandlers.print());
    }
}

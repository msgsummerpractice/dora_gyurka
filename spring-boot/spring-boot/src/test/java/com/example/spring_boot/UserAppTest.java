package com.example.spring_boot;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.example.config.ConfigProperties;

import com.example.controller.UserController;
import com.example.service.UserService;

@WebMvcTest(controllers = UserController.class)
public class UserAppTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @MockitoBean
    private ConfigProperties configProperties;

    @Test
    public void testEndpointUser() throws Exception {
        when(userService.getAllUsers()).thenReturn(List.of(
                new com.example.model.User("John Doe", "john.doe@example.com"),
                new com.example.model.User("Jane Doe", "jane.doe@example.com")));
        this.mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("John Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Jane Doe"))
                .andDo(MockMvcResultHandlers.print());
    }

}

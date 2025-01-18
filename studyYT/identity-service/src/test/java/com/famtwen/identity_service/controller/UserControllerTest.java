package com.famtwen.identity_service.controller;

import com.famtwen.identity_service.dto.request.UserCreationRequest;
import com.famtwen.identity_service.dto.response.UserResponse;
import com.famtwen.identity_service.repository.UserRepository;
import com.famtwen.identity_service.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@Slf4j
@AutoConfigureMockMvc
@TestPropertySource("/test.properties")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc; // se goi toi api de test

    @MockBean
    private UserService userService;

    // Du lieu dau vao
    private UserCreationRequest request;
    // Du lieu dau ra
    private UserResponse userResponse;

    private LocalDate dob;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void initData() {
        dob = LocalDate.now()
                       .minusYears(16); // du 16 tuoi

        request = UserCreationRequest.builder()
                                     .username("joindoe")
                                     .firstName("Jonh")
                                     .lastName("Doe")
                                     .password("12345678")
                                     .dob(dob)
                                     .build();

        // format userreponse
        userResponse = UserResponse.builder()
                                   .id("e5145141515141")
                                   .username("joindoe")
                                   .firstName("Jonh")
                                   .lastName("Doe")
                                   .dob(dob)
                                   .build();

    }


    @Test
    void createUser_ValidRequest_success() throws Exception {
        log.info("TEST Controller CreateUser: ValidRequest success");

        //GIVEN
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        String content = objectMapper.writeValueAsString(request);

        when(userService.createUser(any())).thenReturn(userResponse);


        //WHEN , THEM
        mockMvc.perform(MockMvcRequestBuilders
                       .post("/users")
                       .contentType(MediaType.APPLICATION_JSON_VALUE)
                       .content(content))
               .andExpect(MockMvcResultMatchers.status()
                                               .isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("code")
                                               .value(1000)
               );

        //THEN
    }


    @Test
    void createUser_usernameInvalid_fail() throws Exception {
        log.info("TEST Controller CreateUser: usernameInvalid_fail");

        request.setUsername("joi");
        //GIVEN
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        String content = objectMapper.writeValueAsString(request);

//        when(userService.createUser(any())).thenReturn(userResponse);


        //WHEN , THEM
        mockMvc.perform(MockMvcRequestBuilders
                       .post("/users")
                       .contentType(MediaType.APPLICATION_JSON_VALUE)
                       .content(content))
               .andExpect(MockMvcResultMatchers.status()
                                               .isBadRequest())
               .andExpect(MockMvcResultMatchers.jsonPath("code")
                                               .value(1003))
               .andExpect(MockMvcResultMatchers.jsonPath("message")
                                               .value("Username must be at least 4 characters")
               );

        //THEN
    }

}

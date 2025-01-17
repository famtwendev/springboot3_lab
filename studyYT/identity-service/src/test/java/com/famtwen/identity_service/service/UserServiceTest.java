package com.famtwen.identity_service.service;

import com.famtwen.identity_service.dto.request.UserCreationRequest;
import com.famtwen.identity_service.dto.response.UserResponse;
import com.famtwen.identity_service.entity.User;
import com.famtwen.identity_service.exception.AppException;
import com.famtwen.identity_service.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestPropertySource("/test.properties")
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    // Du lieu dau vao
    private UserCreationRequest request;

    // Du lieu dau ra
    private UserResponse userResponse;

    private User user;

    private LocalDate dob;

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
        user = User.builder()
                   .id("e5145141515142")
                   .username("joindoe")
                   .firstName("Jonh")
                   .lastName("Doe")
                   .dob(dob)
                   .build();
    }

    @Test
    void createUser_validRequest_success() {
        //GIVEN
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.save(any())).thenReturn(user);

        //WHEN
        var response = userService.createUser(request);

        //THEN
        Assertions.assertEquals("e5145141515142", response.getId());
        Assertions.assertEquals("joindoe", response.getUsername());


    }

    @Test
    void createUser_userExsit_fail() {
        //GIVEN
        when(userRepository.existsByUsername(anyString())).thenReturn(true);

        //WHEN
        var exception = Assertions.assertThrows(AppException.class, () -> userService.createUser(request));

        //THEN
        Assertions.assertEquals(1002, exception.getErrorCode()
                                                 .getCode());

    }
}

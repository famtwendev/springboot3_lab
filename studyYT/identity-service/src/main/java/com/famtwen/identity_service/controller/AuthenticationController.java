package com.famtwen.identity_service.controller;


import com.famtwen.identity_service.dto.request.ApiResponse;
import com.famtwen.identity_service.dto.request.AuthenticationRequest;
import com.famtwen.identity_service.dto.request.IntrospectRequest;
import com.famtwen.identity_service.dto.response.AuthenticationResponse;
import com.famtwen.identity_service.dto.response.IntrospectResponse;
import com.famtwen.identity_service.dto.response.UserResponse;
import com.famtwen.identity_service.service.AuthenticationService;
import com.famtwen.identity_service.service.UserService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AuthenticationController {
    AuthenticationService authenticationService;

    UserService userService;

    @PostMapping("/token")
    ApiResponse<AuthenticationResponse>authenticated(@RequestBody AuthenticationRequest request)
    {
        var result = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder().result(result).build();
    }
    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse>authenticated(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder().result(result).build();
    }

    @GetMapping("/info")
    ApiResponse<UserResponse> getCurrentUser(){
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("Username: {}", username);
        return ApiResponse.<UserResponse>builder()
                .result(userService.getMyinfo(username))
                .build();
    }

}

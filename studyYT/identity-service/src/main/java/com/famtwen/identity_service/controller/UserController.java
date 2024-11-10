package com.famtwen.identity_service.controller;

import com.famtwen.identity_service.dto.request.ApiResponse;
import com.famtwen.identity_service.dto.request.UserCreationRequest;
import com.famtwen.identity_service.dto.request.UserUpdateRequest;
import com.famtwen.identity_service.entity.User;
import com.famtwen.identity_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest request){
        ApiResponse<User> apiResponse = new ApiResponse<>();

        apiResponse.setResult(userService.createUser(request));

        return apiResponse;
    }

    @GetMapping
    public List<User>getUser(){
        return userService.getUser();
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable("userId") String userId)
    {
        return userService.getUser(userId);
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable("userId") String userId, @RequestBody UserUpdateRequest request)
    {
        return  userService.updateUser(userId,request);
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable("userId") String userId)
    {
        userService.deleteUser(userId);
        return "User has been deleted";
    }
}

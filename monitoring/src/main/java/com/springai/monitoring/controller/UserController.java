package com.springai.monitoring.controller;

import com.springai.monitoring.dto.User;
import com.springai.monitoring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/details")
    public ResponseEntity<User> getUser(){
        return ResponseEntity.ok(userService.getUser());
    }
}

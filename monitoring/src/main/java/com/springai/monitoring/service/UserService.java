package com.springai.monitoring.service;

import com.springai.monitoring.dto.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public User getUser() {
        return new User(1,"Aman","Kolkata");
    }
}

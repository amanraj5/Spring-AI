package com.springai.agent.controller;

import com.springai.agent.service.AIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai")
public class AIController {
    @Autowired
    private AIService aiService;

    @GetMapping("/getOrder")
    public ResponseEntity<String> getOrder(){
        return ResponseEntity.ok(aiService.getOrder());
    }
}

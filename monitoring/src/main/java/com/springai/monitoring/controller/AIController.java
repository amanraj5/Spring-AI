package com.springai.monitoring.controller;

import com.springai.monitoring.dto.AIResponse;
import com.springai.monitoring.service.AIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai")
public class AIController {
    @Autowired
    private AIService aiService;

    @PostMapping("/response")
    public ResponseEntity<AIResponse> getResponse(@RequestParam("query") String query){
        return ResponseEntity.ok(aiService.getResponse(query));
    }
}

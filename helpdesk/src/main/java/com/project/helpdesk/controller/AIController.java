package com.project.helpdesk.controller;

import com.project.helpdesk.service.AIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;


@RestController
@RequestMapping("/ai")
public class AIController {

    @Autowired
    private AIService aiService;

    @GetMapping("/response")
    public ResponseEntity<Flux<String>> getAIResponse(@RequestBody String query,
                                                      @RequestHeader("conversationID") String conversationId){
        Flux<String> response = aiService.getResponseFromAssistant(query, conversationId);
        return ResponseEntity.ok(response);
    }
}

package com.ai.prompt_response.controller;

import com.ai.prompt_response.interfaces.InChatService;
import com.ai.prompt_response.model.Tutorial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ChatController {

    @Autowired
    private InChatService chatService;

    @GetMapping("/chat")
    public ResponseEntity<Tutorial> chat(@RequestParam("query") String query){
        Tutorial response = chatService.chat(query);
        return ResponseEntity.ok(response);
    }
}

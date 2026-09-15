package com.ai.first_project.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ChatController {

    private ChatClient openAIChatClient;
    private ChatClient ollamaChatClient;

    public ChatController(@Qualifier("openAIClient") ChatClient openAIClient,@Qualifier("ollamaChatClient") ChatClient ollamaChatModel){
        this.openAIChatClient = openAIClient;
        this.ollamaChatClient = ollamaChatModel;
    }
    @GetMapping("/chat")
    public ResponseEntity<String> chat(@RequestParam("query") String query){
        String response = ollamaChatClient.prompt(query).call().content();
        return ResponseEntity.ok(response);
    }
}

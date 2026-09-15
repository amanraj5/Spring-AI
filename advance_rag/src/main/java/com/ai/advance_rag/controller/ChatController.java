package com.ai.advance_rag.controller;

import com.ai.advance_rag.interfaces.InChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping
public class ChatController {

    @Autowired
    private InChatService chatService;

    @GetMapping("/chat")
    public ResponseEntity<String> getResponse(@RequestParam("query") String query){
        String response = chatService.getResponse(query);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/stream-chat")
    public ResponseEntity<Flux<String>> streamChat(@RequestParam("query") String query){
        return ResponseEntity.ok(chatService.streamChat(query));
    }
}

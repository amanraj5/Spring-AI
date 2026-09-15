package com.ai.memory.controller;

import com.ai.memory.interfaces.InChatService;
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
    public ResponseEntity<String> chat(@RequestParam("query") String query,
                                        @RequestParam("conversationId") String conversationId){
        String response = chatService.chat(query, conversationId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/stream-chat")
    public ResponseEntity<Flux<String>> streamChat(@RequestParam("query") String query){
        return ResponseEntity.ok(chatService.streamChat(query));
    }
}

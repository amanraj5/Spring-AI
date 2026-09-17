package com.tool.ToolCalling.controller;

import com.tool.ToolCalling.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {
    @Autowired
    private ChatService chatService;
    @GetMapping("/chat")
    public String getResponse(@RequestParam("query") String query){
        return chatService.getResponse(query);
    }
}

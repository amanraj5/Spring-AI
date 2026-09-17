package com.tool.ToolCalling.service;

import com.tool.ToolCalling.tools.SimpleDateTimeTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    @Autowired
    private ChatClient chatClient;
    @Autowired
    private SimpleDateTimeTool simpleDateTimeTool;


    public String getResponse(String query) {
        return chatClient.prompt()
                .tools(simpleDateTimeTool)
                .user(query)
                .call()
                .content();
    }
}

package com.springai.agent.service;

import com.springai.agent.tools.OrderTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AIService {
    @Autowired
    private ChatClient chatClient;
    @Autowired OrderTools orderTools;


    public String getOrder() {
        return chatClient.prompt()
                .tools(orderTools)
                .call()
                .content();
    }
}

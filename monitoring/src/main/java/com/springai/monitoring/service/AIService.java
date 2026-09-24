package com.springai.monitoring.service;

import com.springai.monitoring.dto.AIResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AIService {
    @Autowired
    private ChatClient chatClient;

    public AIResponse getResponse(String query) {
        AIResponse aiResponse = new AIResponse(
                chatClient.prompt()
                        .user(query)
                        .call()
                        .content(), true
        );
        return aiResponse;
    }
}

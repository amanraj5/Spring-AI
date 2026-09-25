package com.springai.agent.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AIConfig {

    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClient){
        return chatClient.build();
    }
}

package com.ai.first_project.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AIConfig {

    @Bean("openAIClient")
    public ChatClient openAIChatClient(OpenAiChatModel openAIClient){
        return ChatClient.builder(openAIClient).build();
    }

    @Bean("ollamaChatClient")
    public ChatClient ollamaChatClient(OllamaChatModel ollamaChatClient){
        return ChatClient.builder(ollamaChatClient).build();
    }
}

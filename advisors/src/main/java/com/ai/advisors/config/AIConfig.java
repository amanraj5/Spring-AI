package com.ai.advisors.config;

import com.ai.advisors.advisor.TokenPrintAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AIConfig {

    @Bean("ollamaChatClient")
    public ChatClient ollamaChatClient(ChatClient.Builder builder){
        return builder
                .defaultAdvisors(new TokenPrintAdvisor(), new SimpleLoggerAdvisor(), new SafeGuardAdvisor(List.of("game")))
                .build();
    }
}

package com.tool.ToolCalling.configuration;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ChatConfiguration {

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder){
        return builder.build();
    }

    @Bean
    public RestClient restClient(){
        return RestClient
                .builder()
                .baseUrl("http://api.weatherapi.com/v1")
                .build();
    }
}

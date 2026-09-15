package com.ai.advisors.service;

import com.ai.advisors.interfaces.InChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;


@Service
public class ChatService implements InChatService {
    private ChatClient chatClient;

    @Value("classpath:/prompts/user-message.st")
    Resource user;

    @Value("classpath:/prompts/system-message.st")
    Resource system;


    // Constructor Injection
    public ChatService(@Qualifier("ollamaChatClient") ChatClient ollamaChatClient){
        this.chatClient = ollamaChatClient;
    }


    public String chat(String query){

        return chatClient.prompt()
                // .advisors(new SimpleLoggerAdvisor())
                // We can have advisors here as well, but we are going to put that generic
                .system(system -> system.text(this.system))
                .user(user -> user.text(this.user).param("concept", query))
                .call()
                .content();
    }

    @Override
    public Flux<String> streamChat(String query) {

        return chatClient.prompt()
                .system(s -> s.text(this.system))
                .user(u -> u.text(this.user).param("concept", query))
                .stream()
                .content();
    }


}

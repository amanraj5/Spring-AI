package com.ai.memory.service;

import com.ai.memory.interfaces.InChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
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


    public String chat(String query, String conversationId){

        return chatClient.prompt()
                .system(system -> system.text(this.system))
                .user(user -> user.text(this.user).param("concept", query))
                .advisors(advisor -> advisor.param(ChatMemory.CONVERSATION_ID, conversationId))
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

package com.project.helpdesk.service;

import com.project.helpdesk.tools.TicketDatabaseTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class AIService {

    private final ChatClient chatClient;
    private final TicketDatabaseTool ticketTool;

    @Value("classpath:/helpdesk-system.st")
    private Resource systemResource;

    public AIService(ChatClient chatClient, TicketDatabaseTool ticketTool){
        this.chatClient = chatClient;
        this.ticketTool = ticketTool;
    }

    public Flux<String> getResponseFromAssistant(String query, String conversationId){
        return chatClient.prompt()
                .tools(ticketTool)
                .system(systemResource)
                .advisors(advisor -> advisor.param(ChatMemory.CONVERSATION_ID, conversationId))
                .user(query)
                .stream()
                .content();
    }
}

package com.ai.memory.interfaces;


import reactor.core.publisher.Flux;

public interface InChatService {
    String chat(String query, String conversationId);

    Flux<String> streamChat(String query);
}

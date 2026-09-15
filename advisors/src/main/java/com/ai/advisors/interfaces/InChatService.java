package com.ai.advisors.interfaces;


import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;

public interface InChatService {
    String chat(String query);

    Flux<String> streamChat(String query);
}

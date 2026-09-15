package com.ai.advance_rag.interfaces;


import reactor.core.publisher.Flux;

import java.util.List;

public interface InChatService {

    Flux<String> streamChat(String query);

    void saveData(List<String> list);

    String getResponse(String userQuery);
}

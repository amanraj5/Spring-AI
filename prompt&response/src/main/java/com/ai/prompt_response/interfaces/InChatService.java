package com.ai.prompt_response.interfaces;

import com.ai.prompt_response.model.Tutorial;

public interface InChatService {
    Tutorial chat(String query);
}

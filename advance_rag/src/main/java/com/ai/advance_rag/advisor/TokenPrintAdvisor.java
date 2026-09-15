package com.ai.advance_rag.advisor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;
import org.springframework.ai.chat.client.advisor.api.StreamAdvisor;
import org.springframework.ai.chat.client.advisor.api.StreamAdvisorChain;
import reactor.core.publisher.Flux;

public class TokenPrintAdvisor implements CallAdvisor, StreamAdvisor {
    private Logger logger = LoggerFactory.getLogger(TokenPrintAdvisor.class);

    @Override
    public ChatClientResponse adviseCall(ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {
        logger.info("TokenPrintAdvisor is called");
        ChatClientResponse chatClientResponse = callAdvisorChain.nextCall(chatClientRequest);

        // No of tokens inside prompt = user + system + anything else before calling LLM
        logger.info("Input token: "+chatClientResponse.chatResponse().getMetadata().getUsage().getPromptTokens());

        // No of tokens inside output
        logger.info("Output token :"+chatClientResponse.chatResponse().getMetadata().getUsage().getCompletionTokens());

        // Total token = prompt + output
        logger.info("Total token :"+chatClientResponse.chatResponse().getMetadata().getUsage().getTotalTokens());
        return chatClientResponse;
    }

    @Override
    public Flux<ChatClientResponse> adviseStream(ChatClientRequest chatClientRequest, StreamAdvisorChain streamAdvisorChain) {
        Flux<ChatClientResponse> streamAdvisors = streamAdvisorChain.nextStream(chatClientRequest);
        return streamAdvisors;
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public int getOrder() {
        return 0;
    }
}

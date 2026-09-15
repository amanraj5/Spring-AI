package com.ai.advance_rag.service;

import com.ai.advance_rag.interfaces.InChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.rag.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.rag.generation.augmentation.ContextualQueryAugmenter;
import org.springframework.ai.document.Document;
import org.springframework.ai.rag.preretrieval.query.expansion.MultiQueryExpander;
import org.springframework.ai.rag.preretrieval.query.transformation.RewriteQueryTransformer;
import org.springframework.ai.rag.retrieval.join.ConcatenationDocumentJoiner;
import org.springframework.ai.rag.retrieval.search.VectorStoreDocumentRetriever;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;


@Service
public class ChatService implements InChatService {
    private ChatClient chatClient;
    private VectorStore vectorStore;

    @Value("classpath:/prompts/user-message.st")
    Resource user;

    @Value("classpath:/prompts/system-message.st")
    Resource system;


    // Constructor Injection
    public ChatService(@Qualifier("ollamaChatClient") ChatClient ollamaChatClient, VectorStore vectorStore){
        this.chatClient = ollamaChatClient;
        this.vectorStore = vectorStore;
    }

    @Override
    public Flux<String> streamChat(String query) {
        return chatClient.prompt()
                .system(s -> s.text(this.system))
                .user(u -> u.text(this.user).param("concept", query))
                .stream()
                .content();
    }

    @Override
    public void saveData(List<String> list) {
        List<Document> documents = list.stream().map(Document::new).toList();
        this.vectorStore.add(documents);
    }

    @Override
    public String getResponse(String userQuery) {
         Advisor advisors = RetrievalAugmentationAdvisor.builder()
                 // pre-retrieval phase
                 .queryTransformers(RewriteQueryTransformer.builder()
                         .chatClientBuilder(chatClient.mutate().clone())
                         .build())
                 .queryExpander(MultiQueryExpander.builder()
                         .chatClientBuilder(chatClient.mutate().clone())
                         .build())
                 // retrieval phase
                 .documentRetriever(VectorStoreDocumentRetriever.builder()
                         .vectorStore(vectorStore)
                         .topK(3)
                         .similarityThreshold(0.3)
                         .build())
                 // post-retrieval phase
                 .documentJoiner(new ConcatenationDocumentJoiner())
                 .queryAugmenter(ContextualQueryAugmenter.builder().build())
                 .build();



        return chatClient.prompt()
                .advisors(advisors)
                .user(userQuery)
                .call()
                .content();
    }


}

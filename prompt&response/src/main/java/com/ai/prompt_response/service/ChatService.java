package com.ai.prompt_response.service;

import com.ai.prompt_response.interfaces.InChatService;
import com.ai.prompt_response.model.Tutorial;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ChatService implements InChatService {
    private ChatClient chatClient;

    public ChatService(@Qualifier("ollamaChatClient") ChatClient ollamaChatClient){
        this.chatClient = ollamaChatClient;
    }
    public Tutorial chat(String query){
        String prompt = "You are a expert in coding and programing. Always write program in java. Now reply for this question: {query}";

//        String content = chatClient.prompt()
//                .user(prompt)
//                .system("As a expert in cricket.").
//                call().content();
        // By doing this we can use prompt specific means this settings is only for this prompt,
        // if we want the options to be used in every prompt then we can do this during constructor injection
        Prompt prompt1 = new Prompt(prompt, OllamaChatOptions.builder()
                .model("codellama")
                .temperature(0.6)
                .maxTokens(100)
                .build());

        // We can use List<Tutorials> as well to get multiple response
        Tutorial tutorial = chatClient
                .prompt()
                .user(promptUserSpec -> promptUserSpec.text(prompt).param("query", query))
                .call()
                .entity(Tutorial.class);

        return tutorial;
    }

    public String chatTemplate(){

        // First Step -> PromptTemplate
        PromptTemplate promptTemplate = PromptTemplate.builder().template("What is {technology}? Tell me the example of {example}.").build();
        SystemPromptTemplate systemPromptTemplate = SystemPromptTemplate.builder().template("This is for setting system template.").build();

        // Second Step -> RenderTemplate
//        String userMessage = promptTemplate.render(Map.of(
//                "technology", "Spring",
//                "example", "SpringBoot"
//        ));

        Message userMessage = promptTemplate.createMessage(Map.of(
                "technology", "Spring",
                "example", "SpringBoot"
        ));

        Message systemMsg = systemPromptTemplate.createMessage();
        // Third Step -> Build Prompt
        Prompt prompt = new Prompt(systemMsg, userMessage);

        // Last Step -> Build content
//        String content = chatClient.prompt(prompt).call().content();

        // This is the Fluent API method of doing the same thing
//        return chatClient.prompt()
//                .system(system -> system.text("You are coding assistant."))
//                .user(user -> user.text("Tell me about benefits of {tech}.")
//                        .param("tech","java"))
//                .call()
//                .content();
        return chatClient.prompt(prompt).call().content();
    }
}

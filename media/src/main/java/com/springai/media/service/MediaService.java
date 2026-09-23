package com.springai.media.service;

import com.google.genai.Chat;
import org.springframework.ai.audio.transcription.TranscriptionModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.google.genai.GoogleGenAiChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class MediaService {

//    @Autowired
    private TranscriptionModel transcriptionModel;

    private ChatClient chatClient;

    public MediaService(ChatClient.Builder chatClient){
        this.chatClient = chatClient.build();
    }

    public String googleGenAiResponse(String query){
        return chatClient.prompt()
                .options(GoogleGenAiChatOptions.builder())
                .user(query)
                .call()
                .content();
    }


    public String getTranscript(Resource inputAudio) {
        return transcriptionModel.transcribe(inputAudio);
    }

    public String getTranscriptWithOptions(Resource inputAudio) {

//        return transcriptionModel.transcribe(inputAudio, OpenAiAudioTranscriptionOptions.builder()
//                .language("en")
//                .temperature(0.7f)
//                .prompt("Spring boot ")
//                .build());

        return transcriptionModel.transcribe(inputAudio);
    }
}

package com.springai.media.service;

import org.springframework.ai.audio.transcription.TranscriptionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class MediaService {

    @Autowired
    private TranscriptionModel transcriptionModel;


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

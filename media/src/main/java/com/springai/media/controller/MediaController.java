package com.springai.media.controller;

import com.springai.media.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/media")
public class MediaController {
    @Autowired
    private MediaService mediaService;

    @PostMapping("/getMedia")
    public ResponseEntity<String> getMedia(@RequestParam("audioFile") MultipartFile audioFile){
        String response = mediaService.getTranscript(audioFile.getResource());
        return ResponseEntity.ok(response);
    }


    @PostMapping("/transcript")
    public ResponseEntity<String> getTranscript(@Value("${classpath:sample.m4a}") Resource inputAudio){
        String response = mediaService.getTranscript(inputAudio);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/transcript/options")
    public ResponseEntity<String> getTranscriptWithOptions(@Value("${classpath:sample2.m4a}") Resource inputAudio){
        String response = mediaService.getTranscriptWithOptions(inputAudio);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/google")
    ResponseEntity<String> googleGemini(@RequestParam("query") String query){
        return ResponseEntity.ok(mediaService.googleGenAiResponse(query));
    }


}

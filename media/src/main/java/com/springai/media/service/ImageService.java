package com.springai.media.service;

import org.springframework.ai.google.genai.image.GoogleGenAiImageOptions;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    private final ImageModel imageModel;

    public ImageService(ImageModel imageModel){
        this.imageModel = imageModel;
    }

    public String generateImage(String prompt){

        GoogleGenAiImageOptions imageOptions = GoogleGenAiImageOptions.builder()
                .n(1)
                .build();

        ImagePrompt imagePrompt = new ImagePrompt(prompt, imageOptions);

        ImageResponse response = imageModel.call(imagePrompt);

        return "Image Generated";
    }
}

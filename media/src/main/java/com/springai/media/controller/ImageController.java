package com.springai.media.controller;

import com.springai.media.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ai")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("/image")
    public ResponseEntity<String> generateImage(@RequestParam("prompt") String prompt){
        return ResponseEntity.ok(imageService.generateImage(prompt));
    }
}

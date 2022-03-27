package com.teamtbd.cosmetics.image.controller;

import com.teamtbd.cosmetics.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

@RestController
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @PostMapping("/saveReviewImg")
    public Path saveReviewImage( @RequestParam("image") MultipartFile imageFile) throws Exception {
        return imageService.saveReviewImage(imageFile);
    }
}

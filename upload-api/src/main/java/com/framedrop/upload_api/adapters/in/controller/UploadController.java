package com.framedrop.upload_api.adapters.in.controller;

import com.framedrop.upload_api.core.domain.ports.in.TokenInputPort;
import com.framedrop.upload_api.core.domain.ports.in.UploadVideoInputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor
public class UploadController {

    private final UploadVideoInputPort uploadVideoInputPort;
    private final TokenInputPort tokenInputPort;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadVideo(@RequestHeader("Authorization") String bearerToken, @RequestParam("videoFile") MultipartFile videoFile) {
        if (videoFile == null || videoFile.isEmpty()) {
            return org.springframework.http.ResponseEntity.badRequest().body("No file provided");
        }

        String userId = tokenInputPort.getUserIdFromToken(bearerToken);
        uploadVideoInputPort.uploadVideo(videoFile,userId);

        String contentType = videoFile.getContentType();
        if (contentType == null || !contentType.startsWith("video/")) {
            return org.springframework.http.ResponseEntity.badRequest().body("Invalid file type");
        }

        try {
            Path uploadDir = Files.createTempDirectory("uploads");
            Path target = uploadDir.resolve(videoFile.getOriginalFilename());
            Files.copy(videoFile.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
            return org.springframework.http.ResponseEntity.ok("File uploaded to: " + target.toString());
        } catch (IOException e) {
            return org.springframework.http.ResponseEntity.status(500).body("Failed to save file");
        }
    }
}

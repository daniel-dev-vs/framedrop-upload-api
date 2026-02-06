package com.framedrop.upload_api.adapters.in.controller;

import com.framedrop.upload_api.adapters.in.controller.dto.VideoDTO;
import com.framedrop.upload_api.core.domain.ports.in.VideoInputPort;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/videos")
@RequiredArgsConstructor
public class VideoController {

    private final VideoInputPort videoInputPort;

    @GetMapping("/{id}")
    public ResponseEntity<List<VideoDTO>> getVideos(@PathParam("id") String id) {

        return ResponseEntity.ok(videoInputPort.getAllVideosByUserId(id));
    }
}

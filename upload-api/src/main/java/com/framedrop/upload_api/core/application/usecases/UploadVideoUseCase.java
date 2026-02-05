package com.framedrop.upload_api.core.application.usecases;

import com.framedrop.upload_api.adapters.out.dynamodb.UploadVideoDynamoAdapter;
import com.framedrop.upload_api.core.domain.model.UploadVideo;
import com.framedrop.upload_api.core.domain.model.enums.StatusProcess;
import com.framedrop.upload_api.core.domain.ports.in.UploadVideoInputPort;
import com.framedrop.upload_api.core.domain.ports.out.UploadVideoOutputPort;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.UUID;


public class UploadVideoUseCase implements UploadVideoInputPort {

    private final UploadVideoOutputPort uploadVideoOutputPort;

    private final UploadVideoDynamoAdapter uploadVideoDynamoAdapter;

    public UploadVideoUseCase(UploadVideoOutputPort uploadVideoOutputPort, UploadVideoDynamoAdapter uploadVideoDynamoAdapter) {
        this.uploadVideoOutputPort = uploadVideoOutputPort;
        this.uploadVideoDynamoAdapter = uploadVideoDynamoAdapter;
    }

    @Override
    public String uploadVideo(MultipartFile videoFile, String userId) {
        try {
            UploadVideo newUploadVideo = new UploadVideo(
                    UUID.randomUUID().toString(),
                    userId,
                    "username_placeholder",
                    "videos/"+userId,
                    videoFile.getOriginalFilename(),
                    ".mp4",
                    LocalDateTime.now(),
                    StatusProcess.PROCESSING);

            uploadVideoDynamoAdapter.saveUploadVideoMetadata(newUploadVideo);
            return uploadVideoOutputPort.uploadVideoToStorage(userId, videoFile);
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload video", e);
        }
    }
}

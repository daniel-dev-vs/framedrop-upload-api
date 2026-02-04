package com.framedrop.upload_api.core.application.usecases;

import com.framedrop.upload_api.core.domain.ports.in.UploadVideoInputPort;
import com.framedrop.upload_api.core.domain.ports.out.UploadVideoOutputPort;
import org.springframework.web.multipart.MultipartFile;


public class UploadVideoUseCase implements UploadVideoInputPort {

    private final UploadVideoOutputPort uploadVideoOutputPort;


    public UploadVideoUseCase(UploadVideoOutputPort uploadVideoOutputPort) {
        this.uploadVideoOutputPort = uploadVideoOutputPort;
    }

    @Override
    public String uploadVideo(MultipartFile videoFile, String userId) {
        try {
            return uploadVideoOutputPort.uploadVideoToStorage(userId, videoFile);
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload video", e);
        }
    }
}

package com.framedrop.upload_api.core.domain.ports.in;

import org.springframework.web.multipart.MultipartFile;

public interface UploadVideoInputPort {
    String uploadVideo(MultipartFile videoFile, String userId);
}

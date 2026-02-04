package com.framedrop.upload_api.core.domain.ports.out;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadVideoOutputPort {
    String uploadVideoToStorage(String videoFilePath, MultipartFile videoFile) throws IOException;
}

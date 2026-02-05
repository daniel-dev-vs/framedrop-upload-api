package com.framedrop.upload_api.core.domain.ports.out;

import com.framedrop.upload_api.core.domain.model.UploadVideo;

import java.util.List;

public interface UploadVideoMetadataOutputPort {

    UploadVideo getByUploadVideoByUserId(String userId);
    void saveUploadVideoMetadata(UploadVideo uploadVideo);
    List<UploadVideo> getAllUploadVideosString();

}

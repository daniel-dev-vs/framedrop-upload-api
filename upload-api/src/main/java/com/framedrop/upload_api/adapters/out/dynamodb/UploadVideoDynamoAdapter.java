package com.framedrop.upload_api.adapters.out.dynamodb;

import com.framedrop.upload_api.adapters.out.dynamodb.entity.UploadVideoEntity;
import com.framedrop.upload_api.adapters.out.dynamodb.repository.UploadVideoRepository;
import com.framedrop.upload_api.core.domain.model.UploadVideo;
import com.framedrop.upload_api.core.domain.ports.out.UploadVideoMetadataOutputPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UploadVideoDynamoAdapter implements UploadVideoMetadataOutputPort {

    UploadVideoRepository repository;

    public UploadVideoDynamoAdapter(UploadVideoRepository repository) {
        this.repository = repository;
    }

    @Override
    public UploadVideo getByUploadVideoByUserId(String userId) {
        UploadVideoEntity dynamoEntity = repository.getById(userId);

        return new UploadVideo(dynamoEntity.getUploadVideoId(),
                dynamoEntity.getUserId(),
                dynamoEntity.getUserName(),
                dynamoEntity.getVideoPath(),
                dynamoEntity.getFileName(),
                dynamoEntity.getFileExtension(),
                dynamoEntity.getDateUploaded(),
                dynamoEntity.getStatusProcess());
    }

    @Override
    public void saveUploadVideoMetadata(UploadVideo uploadVideo) {
        UploadVideoEntity entity = new UploadVideoEntity();
        entity.setUploadVideoId(uploadVideo.getUploadVideoId());
        entity.setUserId(uploadVideo.getUserId());
        entity.setUserName(uploadVideo.getUserName());
        entity.setVideoPath(uploadVideo.getVideoPath());
        entity.setFileName(uploadVideo.getFileName());
        entity.setFileExtension(uploadVideo.getFileExtension());
        entity.setDateUploaded(uploadVideo.getDateUploaded());
        entity.setStatusProcess(uploadVideo.getStatusProcess());

        repository.save(entity);

    }

    @Override
    public List<UploadVideo> getAllUploadVideosString() {
        return repository.listAll().stream().map(dynamoEntity -> new UploadVideo(
                dynamoEntity.getUploadVideoId(),
                dynamoEntity.getUserId(),
                dynamoEntity.getUserName(),
                dynamoEntity.getVideoPath(),
                dynamoEntity.getFileName(),
                dynamoEntity.getFileExtension(),
                dynamoEntity.getDateUploaded(),
                dynamoEntity.getStatusProcess()
        )).toList();
    }
}

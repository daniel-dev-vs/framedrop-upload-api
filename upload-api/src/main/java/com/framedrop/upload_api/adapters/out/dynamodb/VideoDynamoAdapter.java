package com.framedrop.upload_api.adapters.out.dynamodb;

import com.framedrop.upload_api.adapters.out.dynamodb.entity.VideoEntity;
import com.framedrop.upload_api.adapters.out.dynamodb.repository.VideoRepository;
import com.framedrop.upload_api.core.domain.model.Video;
import com.framedrop.upload_api.core.domain.ports.out.VideoOutputPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VideoDynamoAdapter implements VideoOutputPort {

    VideoRepository repository;

    public VideoDynamoAdapter(VideoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Video> getVideosByUserId(String userId) {

        return repository.listAll().stream().map(dynamoEntity -> new Video(
                dynamoEntity.getVideoId(),
                dynamoEntity.getUserId(),
                dynamoEntity.getUserName(),
                dynamoEntity.getVideoPath(),
                dynamoEntity.getFileName(),
                dynamoEntity.getDateUploaded(),
                dynamoEntity.getStatusProcess()
        )).toList();
    }

    @Override
    public void save(Video video) {
        VideoEntity entity = new VideoEntity();
        entity.setVideoId(video.getVideoId());
        entity.setUserId(video.getUserId());
        entity.setUserName(video.getUserName());
        entity.setVideoPath(video.getVideoPath());
        entity.setFileName(video.getFileName());
        entity.setFileExtension(video.getFileExtension());
        entity.setDateUploaded(video.getDateUploaded());
        entity.setStatusProcess(video.getStatusProcess());

        repository.save(entity);

    }

    @Override
    public List<Video> getAll() {
        return repository.listAll().stream().map(dynamoEntity -> new Video(
                dynamoEntity.getVideoId(),
                dynamoEntity.getUserId(),
                dynamoEntity.getUserName(),
                dynamoEntity.getVideoPath(),
                dynamoEntity.getFileName(),
                dynamoEntity.getDateUploaded(),
                dynamoEntity.getStatusProcess()
        )).toList();
    }
}

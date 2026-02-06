package com.framedrop.upload_api.core.domain.ports.out;

import com.framedrop.upload_api.core.domain.model.Video;

import java.util.List;

public interface VideoOutputPort {

    List<Video> getVideosByUserId(String userId);
    void save(Video video);
    List<Video> getAll();

}

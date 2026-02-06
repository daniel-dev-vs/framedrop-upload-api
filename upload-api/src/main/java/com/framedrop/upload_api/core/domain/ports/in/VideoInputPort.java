package com.framedrop.upload_api.core.domain.ports.in;

import com.framedrop.upload_api.adapters.in.controller.dto.VideoDTO;

import java.util.List;

public interface VideoInputPort {
    List<VideoDTO> getAllVideosByUserId(String userId);
}

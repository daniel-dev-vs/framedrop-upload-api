package com.framedrop.upload_api.adapters.out;

import com.framedrop.upload_api.adapters.out.dto.VideoMetadata;
import com.framedrop.upload_api.core.domain.ports.out.VideoProcessQueueOutPut;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import tools.jackson.databind.ObjectMapper;

@Component
public class SqsVideoQueueAdapter implements VideoProcessQueueOutPut {

    private final SqsClient sqsClient;
    private final ObjectMapper objectMapper;

    public SqsVideoQueueAdapter(SqsClient sqsClient, ObjectMapper objectMapper) {
        this.sqsClient = sqsClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public void pushToQueue(VideoMetadata videoMetadata) {
        String jsonMessage = objectMapper.writeValueAsString(videoMetadata);

        SendMessageRequest sendMsqsageRequest = SendMessageRequest.builder()
                .queueUrl("https://sqs.us-east-1.amazonaws.com/356821122440/VideoProcessingQueue")
                .messageBody(jsonMessage)
                .delaySeconds(0)
                .build();

        sqsClient.sendMessage(sendMsqsageRequest);
    }
}

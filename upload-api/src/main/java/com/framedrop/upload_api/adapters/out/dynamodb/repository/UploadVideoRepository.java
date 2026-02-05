package com.framedrop.upload_api.adapters.out.dynamodb.repository;

import com.framedrop.upload_api.adapters.out.dynamodb.entity.UploadVideoEntity;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class UploadVideoRepository {

    private final DynamoDbTable<UploadVideoEntity> uploadVideoEntityDynamoDbTable;


    public UploadVideoRepository(DynamoDbEnhancedClient enhancedClient) {
        this.uploadVideoEntityDynamoDbTable = enhancedClient.table("UploadVideo", TableSchema.fromBean(UploadVideoEntity.class));
    }

    public void save(UploadVideoEntity  uploadVideoEntity){
        uploadVideoEntityDynamoDbTable.putItem(uploadVideoEntity);
    }

    public UploadVideoEntity getById(String uploadVideoId){
        return uploadVideoEntityDynamoDbTable.getItem(r -> r.key(k -> k.partitionValue(uploadVideoId)));
    }

    public List<UploadVideoEntity> listAll() {
        return StreamSupport.stream(uploadVideoEntityDynamoDbTable
                        .scan()
                        .items()
                        .spliterator(), false)
                .collect(Collectors.toList());
    }


}

package com.framedrop.upload_api.core.domain.ports.in;

public interface TokenInputPort {

    String getUserIdFromToken(String token);

}

package com.framedrop.upload_api.core.application.usecases;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.framedrop.upload_api.core.domain.ports.in.TokenInputPort;
import org.springframework.util.StringUtils;

public class TokenUseCase implements TokenInputPort {
    @Override
    public String getUserIdFromToken(String token) {
        if(!StringUtils.hasText(token)){
            throw new IllegalArgumentException("Token cannot be null or empty");
        }
        if(token.contains("Bearer ")){
            token = token.substring(7);
        }
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getClaim("sub").asString();
    }
}

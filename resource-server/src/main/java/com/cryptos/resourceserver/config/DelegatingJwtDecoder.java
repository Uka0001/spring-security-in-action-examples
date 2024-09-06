package com.cryptos.resourceserver.config;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;

import java.util.List;

public class DelegatingJwtDecoder implements JwtDecoder {

    private final List<JwtDecoder> jwtDecoders;

    public DelegatingJwtDecoder(List<JwtDecoder> jwtDecoders) {
        this.jwtDecoders = jwtDecoders;
    }

    @Override
    public Jwt decode(String token) throws JwtException {
        for (JwtDecoder jwtDecoder : jwtDecoders) {
            try {
                return jwtDecoder.decode(token);
            } catch (JwtException ignored) {
                // Try the next decoder
            }
        }
        throw new JwtException("Unable to decode JWT");
    }
}

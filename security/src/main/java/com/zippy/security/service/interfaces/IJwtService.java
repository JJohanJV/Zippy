package com.zippy.security.service.interfaces;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.function.Function;

public interface IJwtService {
    String getToken(UserDetails user);

    boolean isTokenValid(String token, UserDetails userDetails);

    boolean validateToken(String token);

    <T> T getClaim(String token, Function<Claims, T> claimsResolver);
}

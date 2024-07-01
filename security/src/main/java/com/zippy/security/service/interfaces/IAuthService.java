package com.zippy.security.service.interfaces;

import com.zippy.security.DTO.AuthResponse;
import com.zippy.security.DTO.LoginRequest;
import com.zippy.security.model.Credential;

public interface IAuthService {
    String login(LoginRequest loginRequest);

    AuthResponse register(Credential credential);
}

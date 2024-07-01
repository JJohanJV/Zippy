package com.zippy.security.controller;

import com.zippy.security.DTO.AuthResponse;
import com.zippy.security.DTO.LoginRequest;
import com.zippy.security.DTO.RegisterRequest;
import com.zippy.security.mappers.RegisterMapper;
import com.zippy.security.service.impl.JwtService;
import com.zippy.security.service.interfaces.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private IAuthService authService;
    private JwtService jwtService;
    private RegisterMapper registerMapper;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(AuthResponse.builder().token(authService.login(request)).build()
        );
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        if (request.getPassword() == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }
        if (request.getUsername() == null) {
            throw new IllegalArgumentException("Username cannot be null");
        }
        return ResponseEntity.ok(authService.register(registerMapper.RegisterRequestToCredential(request)));
    }

    @GetMapping("/validate")
    public ResponseEntity validateToken(@RequestParam String token) {
        return jwtService.validateToken(token) ?
                ResponseEntity.ok("Valid") :
                ResponseEntity.badRequest().body("Invalid");
    }

    @Autowired
    public void setAuthService(IAuthService authService) {
        this.authService = authService;
    }

    @Autowired
    public void setJwtService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Autowired
    public void setRegisterMapper(RegisterMapper registerMapper) {
        this.registerMapper = registerMapper;
    }

}

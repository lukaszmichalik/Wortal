package com.springboot.services;

import com.springboot.payload.request.LoginRequest;
import com.springboot.payload.request.SignupRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<?> authenticateUser(LoginRequest loginRequest);
    ResponseEntity<?> registerUser(SignupRequest signUpRequest);
}

package com.project.workmanagemantSystem.service;

import com.project.workmanagemantSystem.Responce.ApiResponse;
import com.project.workmanagemantSystem.domain.User;
import com.project.workmanagemantSystem.domain.request.PasswordRequest;
import com.project.workmanagemantSystem.security.AuthenticationRequest;
import com.project.workmanagemantSystem.security.AuthenticationResponce;
import com.project.workmanagemantSystem.security.RegisterRequest;

import java.util.UUID;

public interface AuthenticationService {
    ApiResponse register(RegisterRequest request);

    AuthenticationResponce authenticate(AuthenticationRequest request);

    ApiResponse getUserVerified(UUID userId);

    User getLoggedUserDetails();

    ApiResponse changePassword(PasswordRequest passwordRequest);

}

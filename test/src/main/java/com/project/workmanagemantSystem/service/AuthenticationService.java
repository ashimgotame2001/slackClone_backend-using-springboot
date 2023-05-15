package com.project.workmanagemantSystem.service;

import com.project.workmanagemantSystem.domain.User;
import com.project.workmanagemantSystem.security.AuthenticationRequest;
import com.project.workmanagemantSystem.security.AuthenticationResponce;
import com.project.workmanagemantSystem.security.RegisterRequest;

import java.util.UUID;

public interface AuthenticationService {
    AuthenticationResponce register(RegisterRequest request);

    AuthenticationResponce authenticate(AuthenticationRequest request);

    String getUserVerified(UUID userId);

    User getLoggedUserDetails();

}

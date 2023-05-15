package com.project.workmanagemantSystem.service.Impl;

import com.project.workmanagemantSystem.config.JwtService;
import com.project.workmanagemantSystem.domain.User;
import com.project.workmanagemantSystem.domain.enumeration.UserRole;
import com.project.workmanagemantSystem.repository.UserRepository;
import com.project.workmanagemantSystem.security.AuthenticationRequest;
import com.project.workmanagemantSystem.security.AuthenticationResponce;
import com.project.workmanagemantSystem.security.RegisterRequest;
import com.project.workmanagemantSystem.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AuthenicationServiceImpl implements AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public AuthenticationResponce register(RegisterRequest request) {

        Optional<User> getUser = userRepository.findByEmail(request.getEmail());
        if (getUser.isEmpty()) {
            User user = User.builder()
                    .id(UUID.randomUUID())
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .Phone(request.getPhone())
                    .status("PENDING")
                    .role(UserRole.USER)
                    .build();
            userRepository.save(user);
            String jwtToken = jwtService.generateToken(user);
            return AuthenticationResponce.builder()
                    .token(jwtToken)
                    .status(HttpStatus.CREATED)
                    .message("USER_SUCCESSFULLY_CREATED")
                    .build();
        }
        return AuthenticationResponce.builder().status(HttpStatus.ALREADY_REPORTED).message("EMAIL_ALREADY_EXISTES").token("token generation fail").build();
    }

    @Override
    public AuthenticationResponce authenticate(AuthenticationRequest request){
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponce.builder()
                .token(jwtToken)
                .message("LOGGED_IN")
                .status(HttpStatus.ACCEPTED)
                .build();
    }

    @Override
    public String getUserVerified(UUID userId) {
        User user = userRepository.getReferenceById(userId);
        if(user != null) {
            user.setStatus("ACTIVE");
            userRepository.save(user);
            return "User verified";
        }
        return "Error";
    }

    @Override
    public User getLoggedUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        User user = userRepository.findByEmail(username).orElseThrow();
        return user;
    }
}

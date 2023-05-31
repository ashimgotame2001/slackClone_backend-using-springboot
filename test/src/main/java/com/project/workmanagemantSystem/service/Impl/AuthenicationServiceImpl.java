package com.project.workmanagemantSystem.service.Impl;

import com.project.workmanagemantSystem.Responce.ApiResponse;
import com.project.workmanagemantSystem.config.JwtService;
import com.project.workmanagemantSystem.domain.Members;
import com.project.workmanagemantSystem.domain.User;
import com.project.workmanagemantSystem.domain.enumeration.Status;
import com.project.workmanagemantSystem.domain.enumeration.UserRole;
import com.project.workmanagemantSystem.domain.request.PasswordRequest;
import com.project.workmanagemantSystem.domain.request.UserRequest;
import com.project.workmanagemantSystem.exceptions.BadAlertException;
import com.project.workmanagemantSystem.repository.MemberRepository;
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
    private final MemberRepository memberRepository;
//    private final MailService service;

    @Override
    public ApiResponse register(RegisterRequest request) {

        Optional<User> getUser = userRepository.findByEmail(request.getEmail());
        if (!getUser.isPresent()) {
            User user = User.builder()
                    .id(UUID.randomUUID())
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .Phone(request.getPhone())
                    .status(Status.PENDING.name())
                    .role(UserRole.USER)
                    .isPasswordChanged(false)
                    .build();
            userRepository.save(user);
            return ApiResponse.builder().message("Please check email to verify").status(HttpStatus.CREATED).build();
        } else {
            throw new BadAlertException(
                    "Email already exists",
                    "user",
                    "EMAIL_ALREADY_EXISTS"
            );
        }
    }

    @Override
    public AuthenticationResponce authenticate(AuthenticationRequest request) {
        User user = userRepository.findByUsersEmail(request.getEmail())
                .orElseThrow(() -> new BadAlertException(
                        "Bad credentials",
                        "USER",
                        "INVALID_CREDENTIALS"
                ));
        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
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
        } else {
            throw new BadAlertException(
                    "Incorrect password",
                    "user",
                    "INVALID_PASSWORD"
            );
        }
    }

    @Override
    public ApiResponse getUserVerified(UUID userId) {
        User user = userRepository.getReferenceById(userId);
        if (user != null) {
            user.setStatus(Status.VERIFIED.name());
            userRepository.save(user);
            return ApiResponse.builder()
                    .message("User Verified")
                    .status(HttpStatus.OK)
                    .build();
        }
        return ApiResponse.builder().build();
    }

    @Override
    public User getLoggedUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        User user = userRepository.findByEmail(username).orElseThrow();
        return user;
    }

    @Override
    public ApiResponse changePassword(PasswordRequest passwordRequest) {
        User user = userRepository.findById(passwordRequest.getUserCode())
                .orElseThrow(() -> new BadAlertException(
                        "User not found",
                        "User",
                        "INVALID_USER_CODE"
                ));
        if (user.getIsPasswordChanged()) {
            if (passwordRequest.getOldPassword() != null) {
                if (passwordEncoder.matches(passwordRequest.getOldPassword(), user.getPassword())) {
                    user.setPassword(passwordEncoder.encode(passwordRequest.getNewPassword()));
                    userRepository.save(user);
                    return ApiResponse.builder()
                            .message("Password Changed Successfully")
                            .status(HttpStatus.OK)
                            .build();
                } else {
                    throw new BadAlertException(
                            "Incorrect password",
                            "USER",
                            "INVALID_USER_PASSWORD"
                    );
                }
            } else {
                throw new BadAlertException(
                        "Old password not found",
                        "USER",
                        "INVALID_USER_PASSWORD"
                );
            }
        } else if (!user.getIsPasswordChanged()) {
            user.setPassword(passwordEncoder.encode(passwordRequest.getNewPassword()));
            user.setIsPasswordChanged(true);
            userRepository.save(user);
            return ApiResponse.builder()
                    .message("Password Changed Successfully")
                    .status(HttpStatus.OK)
                    .build();
        }
        throw new BadAlertException(
                "Bad credentials",
                "User",
                "INVALID_USER_CREDENTIALS"
        );
    }

    @Override
    public ApiResponse updateUserDetails(UserRequest userRequest,UUID userCode) {
        User user =  userRepository.findById(userCode).orElseThrow();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setPhone(userRequest.getPhone());
        userRepository.save(user);
        Members members = memberRepository.findByEmail(user.getEmail()).get();
        members.setFirstName(user.getFirstName());
        members.setLastName(user.getLastName());
        members.setPhone(user.getPhone());
        memberRepository.save(members);
        return ApiResponse.builder().message("User Updated").status(HttpStatus.OK).build();
    }
}

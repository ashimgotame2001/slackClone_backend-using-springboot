package com.project.workmanagemantSystem.service.Impl;

import com.project.workmanagemantSystem.domain.Client;
import com.project.workmanagemantSystem.Responce.ApiResponse;
import com.project.workmanagemantSystem.domain.User;
import com.project.workmanagemantSystem.domain.enumeration.Status;
import com.project.workmanagemantSystem.domain.enumeration.UserRole;
import com.project.workmanagemantSystem.repository.ClientRepository;
import com.project.workmanagemantSystem.repository.UserRepository;
import com.project.workmanagemantSystem.service.ClientService;
import com.project.workmanagemantSystem.utils.RandomCodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ApiResponse CreateNewClient(Client client) {
        String code = RandomCodeGenerator.generateRandomCode();
        System.out.println(code);
        Client newClient = new Client();
        newClient.setOTP(passwordEncoder.encode(code));
        newClient.setId(UUID.randomUUID());
        newClient.setEmail(client.getEmail());
        newClient.setPhone(client.getPhone());
        newClient.setOwner(client.getEmail());
        newClient.setFirstName(client.getFirstName());
        newClient.setLastName(client.getLastName());
        newClient.setStatus(Status.PENDING.name());
        newClient.setStartedAt(LocalDateTime.now());
        clientRepository.save(newClient);

        User user = User.builder()
                .id(UUID.randomUUID())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .Phone(client.getPhone())
                .email(client.getEmail())
                .password(passwordEncoder.encode(RandomCodeGenerator.generateRandomCode()))
                .isPasswordChanged(false)
                .role(UserRole.USER)
                .status(Status.PENDING.name())
                .build();
        userRepository.save(user);

        return ApiResponse.builder().message("client successfully created").status(HttpStatus.CREATED).build();
    }


    @Override
    public Client updateClientsDetails(Client client) {
        return null;
    }

    @Override
    public ApiResponse verifyClientOTP(String code) {
         List<Client> client = clientRepository.findAll();
         ApiResponse apiResponse = new ApiResponse();
         client.forEach(c->{
             if(passwordEncoder.matches(code,c.getOTP())){
                 if(!Objects.equals(c.getStatus(), Status.VERIFIED.name())) {
                     c.setStatus(Status.VERIFIED.name());
                     User user = userRepository.findByEmail(c.getEmail()).get();
                     user.setStatus(Status.VERIFIED.name());
                     userRepository.save(user);
                     clientRepository.save(c);
                     apiResponse.setMessage("OTP Verified");
                     apiResponse.setStatus(HttpStatus.OK);

                     ///// Send change password email to user

                 }else if (c.getStatus().equals(Status.VERIFIED.name())){
                    apiResponse.setMessage("OTP already verified");
                    apiResponse.setStatus(HttpStatus.BAD_REQUEST);
                 }
             } else if (!passwordEncoder.matches(code,c.getOTP())) {
                 apiResponse.setMessage("OTP not found");
                 apiResponse.setStatus(HttpStatus.BAD_REQUEST);
             }
                 }
         );
        return apiResponse;
    }
}


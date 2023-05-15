package com.project.workmanagemantSystem.service.Impl;

import com.project.workmanagemantSystem.domain.Client;
import com.project.workmanagemantSystem.domain.User;
import com.project.workmanagemantSystem.domain.enumeration.Status;
import com.project.workmanagemantSystem.repository.ClientRepository;
import com.project.workmanagemantSystem.repository.UserRepository;
import com.project.workmanagemantSystem.service.ClientService;
import com.project.workmanagemantSystem.utils.RandomCodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String CreateNewClient(Client client) {
        String code = RandomCodeGenerator.generateRandomCode();
        Client newClient = new Client();
        newClient.setOTP(passwordEncoder.encode(code));
        newClient.setId(UUID.randomUUID());
        newClient.setEmail(client.getEmail());
        newClient.setPhone(client.getPhone());
        newClient.setOwner(client.getEmail());
        newClient.setName(client.getName());
        newClient.setStatus(Status.PENDING.name());
        newClient.setStartedAt(LocalDateTime.now());
        clientRepository.save(newClient);

        User user = User.builder()
                .id(UUID.randomUUID())
                .Phone(client.getPhone())
                .email(client.getEmail())
                .password(passwordEncoder.encode(RandomCodeGenerator.generateRandomCode()))
                .build();
        userRepository.save(user);

        return "Client created";
    }


    @Override
    public Client updateClientsDetails(Client client) {
        return null;
    }

    @Override
    public void verifyClientOTP(String code) {
         List<Client> client = clientRepository.findAll();
         client.forEach(c->{
             if(passwordEncoder.matches(code,c.getOTP())){
                 c.setStatus(Status.VERIFIED.name());
                 clientRepository.save(c);
             }
         });
    }
}


package com.project.workmanagemantSystem.resources;


import com.project.workmanagemantSystem.domain.Client;
import com.project.workmanagemantSystem.Responce.ApiResponse;
import com.project.workmanagemantSystem.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.UUID;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientResource {

    private final ClientService clientService;

    @PostMapping("/register")
    ApiResponse registerClient(@RequestBody Client client) throws MessagingException {
        return clientService.CreateNewClient(client);
    };

    @GetMapping("/verify/{clientCode}/{verificationOTP}")
    ApiResponse verifyOTP(@PathVariable String verificationOTP, @PathVariable UUID clientCode){
       return  clientService.verifyClientOTP(verificationOTP,clientCode);
    }
}

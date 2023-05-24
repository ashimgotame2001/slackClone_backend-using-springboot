package com.project.workmanagemantSystem.resources;


import com.project.workmanagemantSystem.domain.Client;
import com.project.workmanagemantSystem.Responce.ApiResponse;
import com.project.workmanagemantSystem.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/provider")
@RequiredArgsConstructor
public class ClientResource {

    private final ClientService clientService;

    @PostMapping("/add_client")
    ApiResponse registerClient(@RequestBody Client client){
        return clientService.CreateNewClient(client);
    };

    @GetMapping("/client/{verificationOTP}")
    ApiResponse verifyOTP(@PathVariable String verificationOTP){
       return   clientService.verifyClientOTP(verificationOTP);
    }
}

package com.project.workmanagemantSystem.resources;


import com.project.workmanagemantSystem.domain.Client;
import com.project.workmanagemantSystem.domain.Response;
import com.project.workmanagemantSystem.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/provider")
@RequiredArgsConstructor
public class ClientResource {

    private final ClientService clientService;

    @PostMapping("/add_client")
    Response registerClient(@RequestBody Client client){
        return clientService.CreateNewClient(client);
    };

    @GetMapping("/client/{verificationOTP}")
    void verifyOTP(@PathVariable String verificationOTP){
         clientService.verifyClientOTP(verificationOTP);
    }
}

package com.project.workmanagemantSystem.service;

import com.project.workmanagemantSystem.domain.Client;
import com.project.workmanagemantSystem.Responce.ApiResponse;

public interface ClientService {

    ApiResponse CreateNewClient(Client client);
    Client updateClientsDetails(Client client);

    ApiResponse verifyClientOTP(String code);

}

package com.project.workmanagemantSystem.service;

import com.project.workmanagemantSystem.domain.Client;
import com.project.workmanagemantSystem.Responce.ApiResponse;

import javax.mail.MessagingException;
import java.util.UUID;

public interface ClientService {

    ApiResponse CreateNewClient(Client client) throws MessagingException;
    Client updateClientsDetails(Client client);

    ApiResponse verifyClientOTP(String code, UUID clientCode);

}

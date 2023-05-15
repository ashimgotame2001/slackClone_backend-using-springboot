package com.project.workmanagemantSystem.service;

import com.project.workmanagemantSystem.domain.Client;

public interface ClientService {

    String CreateNewClient(Client client);
    Client updateClientsDetails(Client client);

    void verifyClientOTP(String code);

}

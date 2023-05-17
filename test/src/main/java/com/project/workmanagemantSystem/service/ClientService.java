package com.project.workmanagemantSystem.service;

import com.project.workmanagemantSystem.domain.Client;
import com.project.workmanagemantSystem.domain.Response;

public interface ClientService {

    Response CreateNewClient(Client client);
    Client updateClientsDetails(Client client);

    void verifyClientOTP(String code);

}

package com.bank.service;

import com.bank.model.Client;

public interface ClientService {

    Client createClient(Client client);

    Client findClient(Integer clientId);
}

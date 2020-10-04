package com.bank.service.impl;

import com.bank.model.Client;
import com.bank.repository.ClientRepository;
import com.bank.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 *
 * This class is an application-layer service responsible for creating
 * new clients
 *
 * this class implements the "create client" use case.
 */
@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * Creates a new client.
     * @param client the client to be created
     */
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }


    /**
     * Search for an existing client.
     * if the client doesn't exists return null
     * @param clientId the if of the client
     */
    public Client findClient(Integer clientId) {
        Optional<Client> client = clientRepository.findById(clientId);
        if(client.isPresent()){
            return client.get();
        }
        return null;
    }
}

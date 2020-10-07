package com.bank.service;


import com.bank.model.Client;
import com.bank.repository.ClientRepository;
import com.bank.service.impl.ClientServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.mockito.MockitoAnnotations.initMocks;
import static org.assertj.core.api.Assertions.assertThat;


public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepositoryMock;

    ClientService clientService;


    @Before
    public void before() {
        initMocks(this);
        clientService = new ClientServiceImpl(clientRepositoryMock);
    }

    @Test
    public void testCreateClient() {
        Client client = new Client("Aymen","SLAMA");
        client.setId(1);
        Optional<Client> clientOptional = Optional.of(client);
        when(clientRepositoryMock.findById(client.getId())).thenReturn(clientOptional);
        when(clientRepositoryMock.save(any(Client.class))).thenReturn(client);


        assertThat(clientService.createClient(client)).isEqualTo(client);
    }
}
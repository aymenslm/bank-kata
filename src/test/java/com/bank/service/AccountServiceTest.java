package com.bank.service;


import com.bank.model.Account;
import com.bank.model.Client;
import com.bank.repository.AccountRepository;
import com.bank.repository.ClientRepository;
import com.bank.repository.OperationRepository;
import com.bank.service.impl.AccountServiceImpl;
import com.bank.service.impl.ClientServiceImpl;
import com.bank.service.impl.OperationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;


import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.mockito.MockitoAnnotations.initMocks;
import static org.assertj.core.api.Assertions.assertThat;

public class AccountServiceTest {

    @Mock
    private OperationRepository operationRepositoryMock;

    @Mock
    private ClientRepository clientRepositoryMock;

    @Mock
    private AccountRepository accountRepositoryMock;

    private AccountService accountService;


    @BeforeEach
    public void before() {
        initMocks(this);
        initMocks(this);
        OperationService operationService = new OperationServiceImpl(operationRepositoryMock);
        ClientService clientService = new ClientServiceImpl(clientRepositoryMock);
        accountService = new AccountServiceImpl(operationService,accountRepositoryMock,clientService);
    }

    @Test
    public void testCreateAccount(){

        Client client = new Client("Aymen","SLAMA");
        client.setId(1);
        Optional<Client> clientOptional = Optional.of(client);
        when(clientRepositoryMock.findById(client.getId())).thenReturn(clientOptional);

        Account account = new Account(client);
        account.setId(UUID.randomUUID().toString());
        when(accountRepositoryMock.save(any(Account.class))).thenReturn(account);

        assertThat(accountService.createAccount(client.getId())).isEqualTo(account);
    }
}
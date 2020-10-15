package com.bank.service;

import com.bank.model.Account;
import com.bank.model.Client;
import com.bank.model.Operation;
import com.bank.repository.AccountRepository;
import com.bank.repository.ClientRepository;
import com.bank.repository.OperationRepository;
import com.bank.service.impl.AccountServiceImpl;
import com.bank.service.impl.ClientServiceImpl;
import com.bank.service.impl.OperationServiceImpl;
import com.bank.service.impl.AccountOperationsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.mockito.MockitoAnnotations.initMocks;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;


public class AccountOperationsServiceTest {

    @Mock
    private AccountRepository accountRepositoryMock;

    @Mock
    private OperationRepository operationRepositoryMock;

    @Mock
    private ClientRepository clientRepositoryMock;

    @InjectMocks
    OperationServiceImpl operationService;

    @InjectMocks
    ClientServiceImpl clientService;

    @Captor
    private ArgumentCaptor<Account> accountCaptor;

    private AccountService accountService;

    private AccountOperationsService accountOperationsService;


    @BeforeEach
    public void before() {
        initMocks(this);
        accountService = new AccountServiceImpl(operationService,accountRepositoryMock,clientService);
        accountOperationsService = new AccountOperationsServiceImpl(operationService,accountService,accountRepositoryMock,operationRepositoryMock);
    }

    @Test
    public void testDepositOperation() {

        Client client = new Client("Aymen","SLAMA");

        Account account = new Account(client);
        account.setId(UUID.randomUUID().toString());
        account.setBalance(new BigDecimal(100));

        Operation operation = new Operation(account,new BigDecimal(100),LocalDateTime.now(),"TEST");
        operation.setId(1);

        Optional<Account> accountOpt = Optional.of(account);
        when(accountRepositoryMock.findById(account.getId())).thenReturn(accountOpt);

        when(operationRepositoryMock.save(any(Operation.class))).thenReturn(operation);


        accountOperationsService.deposit(account.getId(),new BigDecimal(100), LocalDateTime.now());


        verify(accountRepositoryMock).save(accountCaptor.capture());

        Account accountCaptured = accountCaptor.getValue();
        assertThat(accountCaptured.getId()).isEqualTo(account.getId());
        assertThat(accountCaptured.getBalance()).isEqualTo(new BigDecimal(200));
    }

    @Test
    public void testWithdrawalOperation() {


        Client client = new Client("Aymen","SLAMA");

        Account account = new Account(client);
        account.setId(UUID.randomUUID().toString());
        account.setBalance(new BigDecimal(100));

        Operation operation = new Operation(account,new BigDecimal(100).negate(),LocalDateTime.now(),"TEST");
        operation.setId(1);

        Optional<Account> acc = Optional.of(account);
        when(accountRepositoryMock.findById(account.getId())).thenReturn(acc);

        when(operationRepositoryMock.save(any(Operation.class))).thenReturn(operation);


        accountOperationsService.withdrawal(account.getId(),new BigDecimal(100), LocalDateTime.now());



        verify(accountRepositoryMock).save(accountCaptor.capture());

        Account accountCaptured = accountCaptor.getValue();
        assertThat(accountCaptured.getId()).isEqualTo(account.getId());
        assertThat(accountService.getAccountBalance(account.getId())).isEqualTo(new BigDecimal(0));
    }

    @Test
    public void testPrintOperation() {
        Client client = new Client("Aymen","SLAMA");

        Account account = new Account(client);
        account.setId(UUID.randomUUID().toString());
        account.setBalance(new BigDecimal(100));

        Operation operation = new Operation(account,new BigDecimal(100), LocalDateTime.now(),"TEST");
        operation.setId(1);
        List<Operation> operationList = new ArrayList<>();
        operationList.add(operation);

        when(operationRepositoryMock.findAllByAccount_Id(account.getId())).thenReturn(operationList);


        assertThat(accountOperationsService.printStatement(account.getId())).isNotEmpty();
    }
}
package com.bank.service;


import com.bank.model.Account;
import com.bank.model.Client;
import com.bank.model.Operation;
import com.bank.repository.OperationRepository;
import com.bank.service.impl.OperationServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static org.mockito.Mockito.when;

import static org.mockito.MockitoAnnotations.initMocks;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class OperationServiceTest {

    @Mock
    private OperationRepository operationRepositoryMock;

    OperationService operationService;


    @Before
    public void before() {
        initMocks(this);
        operationService = new OperationServiceImpl(operationRepositoryMock);
    }

    @Test
    public void testPrintOperation() {
        Client client = new Client("Aymen","SLAMA");
        // Given
        Account account = new Account(client);
        account.setId(UUID.randomUUID().toString());
        account.setBalance(new BigDecimal(100));

        Operation operation = new Operation(account,new BigDecimal(100), LocalDateTime.now(),"TEST");
        operation.setId(1);
        List<Operation> operationList = new ArrayList<>();
        operationList.add(operation);

        when(operationRepositoryMock.findAllByAccount_Id(account.getId())).thenReturn(operationList);


        assertThat(operationService.printStatement(account.getId())).isNotNull();
    }
}
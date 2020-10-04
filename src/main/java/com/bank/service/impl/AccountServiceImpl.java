package com.bank.service.impl;

import com.bank.model.Account;
import com.bank.model.Client;
import com.bank.model.Operation;
import com.bank.repository.AccountRepository;
import com.bank.service.AccountService;
import com.bank.service.ClientService;
import com.bank.service.OperationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 *
 * This class is an application-layer service responsible for differents
 * operations on bank account
 *
 * this class implements the "deposit and withdrawal" use case.
 */
@Service
public class AccountServiceImpl implements AccountService {

    public static final String DEPOSIT_OPERATION_LABEL = "DEPOSIT";
    public static final String WITHDRAWAL_OPERATION_LABEL = "WITHDRAWAL";


    OperationService operationService;

    AccountRepository accountRepository;

    ClientService clientService;

    public AccountServiceImpl(OperationService operationService,
                              AccountRepository accountRepository,
                              ClientService clientService){
        this.operationService = operationService;
        this.accountRepository = accountRepository;
        this.clientService = clientService;
    }

    /**
     * make a deposit on an account.
     * this operation is transactional
     * it creates a new operation and updates the balance of the account
     * {@link OperationServiceImpl}
     * @param accountId the amount of the operation
     * @param amount the date time o the operation
     * @param date the date time of the operation
     */
    @Transactional
    @Override
    public Integer deposit(String accountId, BigDecimal amount, LocalDateTime date) {
        Account account = findAccount(accountId);
        account.setBalance(account.getBalance().add(amount));
        Operation operation =operationService.recordOperation(amount, date, account, DEPOSIT_OPERATION_LABEL);
        accountRepository.save(account);
        return operation.getId();
    }

    /**
     * Search for an existing account.
     * if the account doesn't exists return null
     * @param accountId the id of the account
     */
    public Account findAccount(String accountId){
        Optional<Account> account = accountRepository.findById(accountId);
        if(account.isPresent()){
            return account.get();
        }
        return null;
    }

    /**
     * make a withdrawal on an account.
     * this operation is transactional
     * it creates a new operation and updates the balance of the account
     * {@link OperationServiceImpl}
     * @param accountId the amount of the operation
     * @param amount the date time o the operation
     * @param date the date time of the operation
     */
    @Transactional
    @Override
    public Integer withdrawal(String accountId, BigDecimal amount, LocalDateTime date) {
        Account account = findAccount(accountId);
        account.setBalance(account.getBalance().add(amount.negate()));
        Operation operation = operationService.recordOperation(amount.negate(), date, account, WITHDRAWAL_OPERATION_LABEL);
        accountRepository.save(account);
        return operation.getId();
    }

    /**
     * Creates a new Account.
     * @param clientId the client of the new account
     */
    @Override
    public Account createAccount(Integer clientId) {
        Client client = clientService.findClient(clientId);
        Account account = new Account(client);
        return accountRepository.save(account);
    }
}

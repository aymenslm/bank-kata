package com.bank.service.impl;

import com.bank.exception.RecordNotFoundException;
import com.bank.model.Account;
import com.bank.model.Client;
import com.bank.repository.AccountRepository;
import com.bank.service.AccountService;
import com.bank.service.ClientService;
import com.bank.service.OperationService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
     * Search for an existing account.
     * if the account doesn't exists return null
     * @param accountId the id of the account
     */
    public Account findAccount(String accountId){
        Optional<Account> account = accountRepository.findById(accountId);
        if(account.isPresent()){
            return account.get();
        }
        throw new RecordNotFoundException(accountId+ " is NOT found");
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


    /**
     * get the current account balance.
     * @param accountId
     */
    @Override
    public BigDecimal getAccountBalance(String accountId) {
        Account account = findAccount(accountId);
        return account.getBalance();
    }
}

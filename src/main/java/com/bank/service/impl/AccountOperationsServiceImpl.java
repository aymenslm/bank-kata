package com.bank.service.impl;

import com.bank.exception.BankServiceException;
import com.bank.model.Account;
import com.bank.model.Operation;
import com.bank.repository.AccountRepository;
import com.bank.repository.OperationRepository;
import com.bank.service.AccountOperationsService;
import com.bank.service.AccountService;
import com.bank.service.OperationService;
import com.bank.util.CurrencyUtil;
import com.bank.util.FileUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountOperationsServiceImpl implements AccountOperationsService {

    public static final String DEPOSIT_OPERATION_LABEL = "DEPOSIT";
    public static final String WITHDRAWAL_OPERATION_LABEL = "WITHDRAWAL";
    public static final String CSV_FILE_NAME = "account-operation.csv";

    OperationService operationService;
    AccountService accountService;
    AccountRepository accountRepository;
    OperationRepository operationRepository;

    public AccountOperationsServiceImpl(OperationService operationService,
                                        AccountService accountService,
                                        AccountRepository accountRepository,
                                        OperationRepository operationRepository){
        this.operationService = operationService;
        this.accountService = accountService;
        this.accountRepository = accountRepository;
        this.operationRepository = operationRepository;
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
        Account account = accountService.findAccount(accountId);
        account.setBalance(account.getBalance().add(amount.negate()));
        Operation operation = operationService.recordOperation(amount.negate(), date, account, WITHDRAWAL_OPERATION_LABEL);
        accountRepository.save(account);
        return operation.getId();
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
        Account account = accountService.findAccount(accountId);
        account.setBalance(account.getBalance().add(amount));
        Operation operation =operationService.recordOperation(amount, date, account, DEPOSIT_OPERATION_LABEL);
        accountRepository.save(account);
        return operation.getId();
    }

    /**
     * print a operations on csv file
     * .
     * @param accountId the accountId of the operations
     */
    public byte[] printStatement(String accountId) {
        List<Operation> operations = operationRepository.findAllByAccount_Id(accountId);
        List<String[]> dataLines = getOperationLines(operations);
        File csvOutputFile = new File(CSV_FILE_NAME);
        try {
            PrintWriter pw = new PrintWriter(csvOutputFile);
            dataLines.stream()
                    .map(FileUtil::convertToCSV)
                    .forEach(pw::println);
            InputStream in = new FileInputStream(csvOutputFile);
            pw.close();
            return IOUtils.toByteArray(in);
        } catch (IOException e){
            throw new BankServiceException(accountId);
        }
    }

    List<String[]> getOperationLines(List<Operation> operations){
        List<String[]> dataLines = new ArrayList<>();
        operations.forEach(operation -> dataLines.add(new String[]
                {
                        operation.getId().toString(),
                        operation.getLabel(),
                        CurrencyUtil.currencyFormat(operation.getAmount()),
                        operation.getCurrency().getCurrencyCode()
                }));
        return dataLines;
    }
}

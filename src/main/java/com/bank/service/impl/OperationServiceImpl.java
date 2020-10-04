package com.bank.service.impl;

import com.bank.exception.BankServiceException;
import com.bank.model.Account;
import com.bank.model.Operation;
import com.bank.repository.OperationRepository;
import com.bank.service.OperationService;
import com.bank.util.CurrencyUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 *
 * This class is an application-layer service responsible for recording or print
 * statements.
 *
 * this class implements the "print statements" use case.
 */
@Service
public class OperationServiceImpl implements OperationService {

    private static String CSV_FILE_NAME = "account-operation.csv";

    OperationRepository operationRepository;

    public OperationServiceImpl(OperationRepository operationRepository){
        this.operationRepository = operationRepository;
    }

    /**
     * Creates a new operation.
     * @param amount the amount of the operation
     * @param dateTime the date time o the operation
     * @param account the account source of the operation
     * @param label the label of the operation DEPOSIT or WITHDRAWAL
     */
    @Override
    public Operation recordOperation(BigDecimal amount, LocalDateTime dateTime, Account account, String label) {
        Operation operation = new Operation(account,amount,dateTime,label);

        return operationRepository.save(operation);
    }

    /**
     * print a operations on csv file
     * .
     * @param accountId the accountId of the operations
     */
    public byte[] printStatement(String accountId) {
        List<Operation> operations = operationRepository.findAllByAccount_Id(accountId);
        List<String[]> dataLines = new ArrayList<>();
        operations.forEach(operation -> dataLines.add(new String[]
                { operation.getId().toString(), operation.getLabel(), CurrencyUtil.currencyFormat(operation.getAmount()), operation.getCurrency().getCurrencyCode() }));
        File csvOutputFile = new File(CSV_FILE_NAME);
        try {
            PrintWriter pw = new PrintWriter(csvOutputFile);
            dataLines.stream()
                    .map(this::convertToCSV)
                    .forEach(pw::println);
            InputStream in = new FileInputStream(csvOutputFile);

            return IOUtils.toByteArray(in);
        } catch (Exception e){
            throw new BankServiceException(accountId);
        }
    }

    public String convertToCSV(String[] data) {
        return Stream.of(data)
                .collect(Collectors.joining(";"));
    }
}

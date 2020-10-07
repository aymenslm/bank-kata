package com.bank.service.impl;

import com.bank.model.Account;
import com.bank.model.Operation;
import com.bank.repository.OperationRepository;
import com.bank.service.OperationService;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 *
 * This class is an application-layer service responsible for recording or print
 * statements.
 *
 * this class implements the "print statements" use case.
 */
@Service
public class OperationServiceImpl implements OperationService {

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
}

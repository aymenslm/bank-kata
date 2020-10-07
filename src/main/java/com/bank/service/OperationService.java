package com.bank.service;

import com.bank.model.Account;
import com.bank.model.Operation;


import java.math.BigDecimal;
import java.time.LocalDateTime;


public interface OperationService {

    Operation recordOperation(BigDecimal amount, LocalDateTime dateTime, Account account, String label);
}

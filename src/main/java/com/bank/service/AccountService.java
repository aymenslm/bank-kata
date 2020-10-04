package com.bank.service;

import com.bank.model.Account;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface AccountService {

    Integer deposit(String accountId, BigDecimal amount, LocalDateTime date);

    Integer withdrawal(String accountId,BigDecimal amount, LocalDateTime date);

    Account createAccount(Integer clientId);

    Account findAccount(String accountId);
}

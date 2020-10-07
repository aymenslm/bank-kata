package com.bank.service;

import com.bank.model.Account;

import java.math.BigDecimal;

public interface AccountService {

    Account createAccount(Integer clientId);

    Account findAccount(String accountId);

    BigDecimal getAccountBalance(String accountId);
}

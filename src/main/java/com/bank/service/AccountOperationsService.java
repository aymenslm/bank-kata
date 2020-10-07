package com.bank.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface AccountOperationsService {

    Integer deposit(String accountId, BigDecimal amount, LocalDateTime date);

    Integer withdrawal(String accountId,BigDecimal amount, LocalDateTime date);

    byte[] printStatement(String accountId);
}

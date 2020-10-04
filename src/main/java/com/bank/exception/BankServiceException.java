package com.bank.exception;


public class BankServiceException extends RuntimeException {

    private static final String MESSAGE = "Exception Occured on account %s";

    public BankServiceException(String accountId) {
        super(String.format(MESSAGE, accountId));
    }

}

package com.bank.controller;

import com.bank.service.AccountOperationsService;
import com.bank.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/account")
public class AccountController {

    AccountService accountService;
    AccountOperationsService accountOperationsService;

    public AccountController(AccountService accountService, AccountOperationsService accountOperationsService){
        this.accountService = accountService;
        this.accountOperationsService = accountOperationsService;
    }

    @ApiOperation(value = "Deposit money")
    @PostMapping("/deposit")
    public ResponseEntity deposit(@RequestParam String accountId, @RequestParam BigDecimal amount) {
        return ResponseEntity.ok(accountOperationsService.deposit(accountId,amount, LocalDateTime.now()));
    }

    @ApiOperation(value = "Withdraw Money")
    @PostMapping("/withdrawal")
    public ResponseEntity withdrawal(@RequestParam String accountId, @RequestParam BigDecimal amount) {
        return ResponseEntity.ok(accountOperationsService.withdrawal(accountId,amount, LocalDateTime.now()));
    }

    @ApiOperation(value = "Create Account")
    @PostMapping("/create")
    public ResponseEntity createAccount(@RequestParam Integer clientId) {

        return ResponseEntity.ok(accountService.createAccount(clientId));
    }

    @ApiOperation(value = "Get current account balance")
    @PostMapping("/balance")
    public ResponseEntity accountBalance(@RequestParam String accountId) {

        return ResponseEntity.ok(accountService.getAccountBalance(accountId));
    }

    @ApiOperation(value = "Find an account")
    @GetMapping("/find/{accountId}")
    public ResponseEntity findAccount(@PathVariable ("accountId") String accountId) {

        return ResponseEntity.ok(accountService.findAccount(accountId));
    }
}

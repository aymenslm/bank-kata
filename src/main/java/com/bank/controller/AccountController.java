package com.bank.controller;


import com.bank.model.Client;
import com.bank.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiOperation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/account")
public class AccountController {

    AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @ApiOperation(value = "Save Money")
    @PostMapping("/deposit")
    public ResponseEntity deposit(@RequestParam String accountId, @RequestParam BigDecimal amount) {
        return ResponseEntity.ok(accountService.deposit(accountId,amount, LocalDateTime.now()));
    }

    @ApiOperation(value = "Save Money")
    @PostMapping("/withdrawal")
    public ResponseEntity withdrawal(@RequestParam String accountId, @RequestParam BigDecimal amount) {
        return ResponseEntity.ok(accountService.withdrawal(accountId,amount, LocalDateTime.now()));
    }

    @ApiOperation(value = "Create Account")
    @PostMapping("/create")
    public ResponseEntity createAccount(@RequestParam Integer clientId) {

        return ResponseEntity.ok(accountService.createAccount(clientId));
    }
}

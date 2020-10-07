package com.bank.controller;

import com.bank.service.AccountOperationsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/operation")
public class OperationController {

    AccountOperationsService accountOperationsService;

    public OperationController(AccountOperationsService accountOperationsService){
        this.accountOperationsService = accountOperationsService;
    }

    @ApiOperation(value = "Operations Account")
    @GetMapping("/print")
    public ResponseEntity<byte[]> printOperations(@RequestParam String accountId) {
        return ResponseEntity.ok(accountOperationsService.printStatement(accountId));
    }
}

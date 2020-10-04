package com.bank.controller;



import com.bank.service.OperationService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;

@RestController
@RequestMapping("/operation")
public class OperationController {

    OperationService operationService;

    public OperationController(OperationService operationService){
        this.operationService = operationService;
    }

    @ApiOperation(value = "Operations Account")
    @GetMapping("/print")
    public ResponseEntity<byte[]> printOperations(@RequestParam String accountId) {
        return ResponseEntity.ok(operationService.printStatement(accountId));
    }
}

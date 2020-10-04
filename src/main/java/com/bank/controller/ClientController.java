package com.bank.controller;

import com.bank.model.Client;
import com.bank.service.ClientService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientController {

    ClientService clientService;

    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @ApiOperation(value = "Create Client")
    @PostMapping("/create")
    public ResponseEntity createClient(@RequestParam String firstname, @RequestParam String lastName) {
        Client client = new Client(firstname,lastName);
        return ResponseEntity.ok(clientService.createClient(client));
    }

}

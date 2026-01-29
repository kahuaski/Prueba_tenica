package com.kahuaski.pruebatecnica.ms_customer_service.controllers;

import com.kahuaski.pruebatecnica.ms_customer_service.entities.Client;
import com.kahuaski.pruebatecnica.ms_customer_service.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/clientes") 
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> getAll() {
        return clientService.findAll();
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Client> getById(@PathVariable String clientId) {
        return ResponseEntity.ok(clientService.findByClientId(clientId));
    }

    @PostMapping
    public ResponseEntity<Client> create(@RequestBody Client client) {
        return new ResponseEntity<>(clientService.save(client), HttpStatus.CREATED);
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<Client> update(@PathVariable String clientId, @RequestBody Client client) {
        return ResponseEntity.ok(clientService.update(clientId, client));
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> delete(@PathVariable String clientId) {
        clientService.delete(clientId);
        return ResponseEntity.noContent().build();
    }
}
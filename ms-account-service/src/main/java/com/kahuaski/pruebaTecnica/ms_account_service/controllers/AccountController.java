package com.kahuaski.pruebaTecnica.ms_account_service.controllers;

import com.kahuaski.pruebaTecnica.ms_account_service.entities.Account;
import com.kahuaski.pruebaTecnica.ms_account_service.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<Account> getAll() {
        return accountService.findAll();
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<Account> getByNumber(@PathVariable String accountNumber) {
        return ResponseEntity.ok(accountService.findByAccountNumber(accountNumber));
    }

    @PostMapping
    public ResponseEntity<Account> create(@RequestBody Account account) {
        return new ResponseEntity<>(accountService.save(account), HttpStatus.CREATED);
    }

    @PutMapping("/{accountNumber}")
    public ResponseEntity<Account> update(@PathVariable String accountNumber, @RequestBody Account account) {
        return ResponseEntity.ok(accountService.update(accountNumber, account));
    }

    @DeleteMapping("/{accountNumber}")
    public ResponseEntity<Void> delete(@PathVariable String accountNumber) {
        accountService.delete(accountNumber);
        return ResponseEntity.noContent().build();
    }
}

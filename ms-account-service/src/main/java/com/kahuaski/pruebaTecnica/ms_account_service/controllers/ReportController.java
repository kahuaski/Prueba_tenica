package com.kahuaski.pruebaTecnica.ms_account_service.controllers;

import com.kahuaski.pruebaTecnica.ms_account_service.dto.AccountReport;
import com.kahuaski.pruebaTecnica.ms_account_service.dto.MovementReport;
import com.kahuaski.pruebaTecnica.ms_account_service.dto.ReportResponse;
import com.kahuaski.pruebaTecnica.ms_account_service.entities.Account;
import com.kahuaski.pruebaTecnica.ms_account_service.entities.Movement;
import com.kahuaski.pruebaTecnica.ms_account_service.repositories.AccountRepository;
import com.kahuaski.pruebaTecnica.ms_account_service.repositories.MovementRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reportes")
public class ReportController {

    private final AccountRepository accountRepository;
    private final MovementRepository movementRepository;

    public ReportController(AccountRepository accountRepository, MovementRepository movementRepository) {
        this.accountRepository = accountRepository;
        this.movementRepository = movementRepository;
    }

    @GetMapping
    public ReportResponse report(
            @RequestParam(value = "cliente", required = false) String cliente,
            @RequestParam(value = "inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam(value = "fin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin
    ) {

        List<Account> accounts = new ArrayList<>();
        if (cliente != null && !cliente.isBlank()) {
            accounts = accountRepository.findAll().stream()
                    .filter(a -> cliente.equals(a.getOwner()))
                    .collect(Collectors.toList());
        } else {
            accounts = accountRepository.findAll();
        }

        List<AccountReport> accountReports = new ArrayList<>();
        for (Account acc : accounts) {
            List<Movement> moves = movementRepository.findByAccountAccountNumberAndDateBetween(acc.getAccountNumber(), inicio, fin);
            List<MovementReport> movementReports = moves.stream()
                    .map(m -> new MovementReport(m.getDate(), m.getMovementType(), m.getAmount(), m.getBalance()))
                    .collect(Collectors.toList());

            AccountReport ar = new AccountReport(acc.getAccountNumber(), acc.getAccountType(), acc.getBalance(), movementReports);
            accountReports.add(ar);
        }

        return new ReportResponse(cliente, accountReports);
    }
}

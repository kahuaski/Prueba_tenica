package com.kahuaski.pruebaTecnica.ms_account_service.controllers;

import com.kahuaski.pruebaTecnica.ms_account_service.dto.MovementRequest;
import com.kahuaski.pruebaTecnica.ms_account_service.entities.Movement;
import com.kahuaski.pruebaTecnica.ms_account_service.services.MovementService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovementController {

    private final MovementService movementService;

    public MovementController(MovementService movementService) {
        this.movementService = movementService;
    }

    @PostMapping
    public ResponseEntity<Movement> register(@RequestBody MovementRequest req) {
        Movement m = new Movement();
        m.setMovementType(req.getMovementType());
        m.setAmount(req.getAmount());
        Movement saved = movementService.registerMovement(req.getAccountNumber(), m);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Movement> getAll() {
        return movementService.findAll();
    }

    @GetMapping("/account/{accountNumber}")
    public List<Movement> getByAccount(@PathVariable String accountNumber) {
        return movementService.findByAccount(accountNumber);
    }

    @GetMapping("/account/{accountNumber}/fecha")
    public List<Movement> getByAccountAndRange(
            @PathVariable String accountNumber,
            @RequestParam("inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam("fin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return movementService.findByAccountAndDateRange(accountNumber, start, end);
    }
}

package com.kahuaski.pruebaTecnica.ms_account_service.services;

import com.kahuaski.pruebaTecnica.ms_account_service.entities.Movement;
import java.time.LocalDateTime;
import java.util.List;

public interface MovementService {
    Movement registerMovement(String accountNumber, Movement movement);
    List<Movement> findAll();
    Movement findById(Long id);
    List<Movement> findByAccount(String accountNumber);
    List<Movement> findByAccountAndDateRange(String accountNumber, LocalDateTime start, LocalDateTime end);
}

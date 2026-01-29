package com.kahuaski.pruebaTecnica.ms_account_service.services;

import com.kahuaski.pruebaTecnica.ms_account_service.entities.Account;
import com.kahuaski.pruebaTecnica.ms_account_service.entities.Movement;
import com.kahuaski.pruebaTecnica.ms_account_service.repositories.AccountRepository;
import com.kahuaski.pruebaTecnica.ms_account_service.repositories.MovementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovementServiceImpl implements MovementService {

    private final MovementRepository movementRepository;
    private final AccountRepository accountRepository;

    public MovementServiceImpl(MovementRepository movementRepository, AccountRepository accountRepository) {
        this.movementRepository = movementRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public Movement registerMovement(String accountNumber, Movement movement) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
            .orElseThrow(() -> new com.kahuaski.pruebaTecnica.ms_account_service.exceptions.ResourceNotFoundException("Account not found: " + accountNumber));

        // Calculate new balance (amount can be positive or negative)
        java.math.BigDecimal newBalance = account.getBalance().add(movement.getAmount());

        if (newBalance.compareTo(java.math.BigDecimal.ZERO) < 0) {
            throw new com.kahuaski.pruebaTecnica.ms_account_service.exceptions.InsufficientFundsException("Saldo no disponible");
        }

        account.setBalance(newBalance);
        accountRepository.save(account);

        movement.setDate(movement.getDate() != null ? movement.getDate() : LocalDateTime.now());
        movement.setBalance(newBalance);
        movement.setAccount(account);

        return movementRepository.save(movement);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Movement> findAll() {
        return movementRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Movement findById(Long id) {
        return movementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movement not found: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Movement> findByAccount(String accountNumber) {
        return movementRepository.findByAccountAccountNumber(accountNumber);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Movement> findByAccountAndDateRange(String accountNumber, LocalDateTime start, LocalDateTime end) {
        return movementRepository.findByAccountAccountNumberAndDateBetween(accountNumber, start, end);
    }
}

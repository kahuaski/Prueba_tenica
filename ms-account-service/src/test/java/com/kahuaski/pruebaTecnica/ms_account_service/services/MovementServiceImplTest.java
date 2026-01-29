package com.kahuaski.pruebaTecnica.ms_account_service.services;

import com.kahuaski.pruebaTecnica.ms_account_service.entities.Account;
import com.kahuaski.pruebaTecnica.ms_account_service.entities.Movement;
import com.kahuaski.pruebaTecnica.ms_account_service.exceptions.InsufficientFundsException;
import com.kahuaski.pruebaTecnica.ms_account_service.repositories.AccountRepository;
import com.kahuaski.pruebaTecnica.ms_account_service.repositories.MovementRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovementServiceImplTest {

    @Mock
    MovementRepository movementRepository;

    @Mock
    AccountRepository accountRepository;

    @InjectMocks
    MovementServiceImpl movementService;

    @Test
    void registerMovement_success() {
        Account acc = new Account();
        acc.setId(1L);
        acc.setAccountNumber("ACC123");
        acc.setBalance(new BigDecimal("100.00"));

        Movement movement = new Movement();
        movement.setAmount(new BigDecimal("-50.00"));
        movement.setMovementType("WITHDRAWAL");
        movement.setDate(LocalDateTime.now());

        when(accountRepository.findByAccountNumber("ACC123")).thenReturn(Optional.of(acc));
        when(accountRepository.save(any(Account.class))).thenAnswer(i -> i.getArgument(0));
        when(movementRepository.save(any(Movement.class))).thenAnswer(i -> {
            Movement m = i.getArgument(0);
            m.setId(10L);
            return m;
        });

        Movement saved = movementService.registerMovement("ACC123", movement);

        assertNotNull(saved);
        assertEquals(10L, saved.getId());
        assertEquals(new BigDecimal("50.00"), acc.getBalance());
        verify(accountRepository).save(acc);
        verify(movementRepository).save(any(Movement.class));
    }

    @Test
    void registerMovement_insufficientFunds() {
        Account acc = new Account();
        acc.setId(1L);
        acc.setAccountNumber("ACC999");
        acc.setBalance(new BigDecimal("20.00"));

        Movement movement = new Movement();
        movement.setAmount(new BigDecimal("-50.00"));
        movement.setMovementType("WITHDRAWAL");

        when(accountRepository.findByAccountNumber("ACC999")).thenReturn(Optional.of(acc));

        assertThrows(InsufficientFundsException.class, () -> movementService.registerMovement("ACC999", movement));
        verify(accountRepository, never()).save(any());
        verify(movementRepository, never()).save(any());
    }
}

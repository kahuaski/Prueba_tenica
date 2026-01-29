package com.kahuaski.pruebaTecnica.ms_account_service.repositories;

import com.kahuaski.pruebaTecnica.ms_account_service.entities.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {
    List<Movement> findByAccountAccountNumber(String accountNumber);
    List<Movement> findByAccountAccountNumberAndDateBetween(String accountNumber, LocalDateTime start, LocalDateTime end);
}

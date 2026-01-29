package com.kahuaski.pruebaTecnica.ms_account_service.repositories;

import com.kahuaski.pruebaTecnica.ms_account_service.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNumber(String accountNumber);
}

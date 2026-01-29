package com.kahuaski.pruebaTecnica.ms_account_service.services;

import com.kahuaski.pruebaTecnica.ms_account_service.entities.Account;
import java.util.List;

public interface AccountService {
    List<Account> findAll();
    Account findByAccountNumber(String accountNumber);
    Account save(Account account);
    Account update(String accountNumber, Account accountDetails);
    void delete(String accountNumber);
}

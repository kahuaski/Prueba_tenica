package com.kahuaski.pruebaTecnica.ms_account_service.services;

import com.kahuaski.pruebaTecnica.ms_account_service.entities.Account;
import com.kahuaski.pruebaTecnica.ms_account_service.repositories.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Account findByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new com.kahuaski.pruebaTecnica.ms_account_service.exceptions.ResourceNotFoundException("Error: Account with number " + accountNumber + " not found."));
    }

    @Override
    @Transactional
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    @Transactional
    public Account update(String accountNumber, Account details) {
        Account existing = findByAccountNumber(accountNumber);
        BeanUtils.copyProperties(details, existing, "id", "accountNumber");
        return accountRepository.save(existing);
    }

    @Override
    @Transactional
    public void delete(String accountNumber) {
        Account acc = findByAccountNumber(accountNumber);
        accountRepository.delete(acc);
    }
}

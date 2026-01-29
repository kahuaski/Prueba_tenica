package com.kahuaski.pruebaTecnica.ms_account_service.dto;

import java.math.BigDecimal;
import java.util.List;

public class AccountReport {
    private String accountNumber;
    private String accountType;
    private BigDecimal balance;
    private List<MovementReport> movements;

    public AccountReport() {}

    public AccountReport(String accountNumber, String accountType, BigDecimal balance, List<MovementReport> movements) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
        this.movements = movements;
    }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }
    public String getAccountType() { return accountType; }
    public void setAccountType(String accountType) { this.accountType = accountType; }
    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }
    public List<MovementReport> getMovements() { return movements; }
    public void setMovements(List<MovementReport> movements) { this.movements = movements; }
}

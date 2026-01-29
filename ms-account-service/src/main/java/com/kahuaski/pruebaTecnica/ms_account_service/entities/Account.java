package com.kahuaski.pruebaTecnica.ms_account_service.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tbl_accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String accountNumber;

    @Column(nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;

    @Column(nullable = false)
    private BigDecimal initialBalance = BigDecimal.ZERO;

    @Column(nullable = false)
    private String accountType;

    private Boolean status = true;

    private String owner;

    public Account() {}

    public Account(String accountNumber, BigDecimal initialBalance, String accountType, Boolean status, String owner) {
        this.accountNumber = accountNumber;
        this.initialBalance = initialBalance != null ? initialBalance : BigDecimal.ZERO;
        this.balance = this.initialBalance;
        this.accountType = accountType;
        this.status = status != null ? status : true;
        this.owner = owner;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }
    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }
    public BigDecimal getInitialBalance() { return initialBalance; }
    public void setInitialBalance(BigDecimal initialBalance) { this.initialBalance = initialBalance; }
    public String getAccountType() { return accountType; }
    public void setAccountType(String accountType) { this.accountType = accountType; }
    public Boolean getStatus() { return status; }
    public void setStatus(Boolean status) { this.status = status; }
    public String getOwner() { return owner; }
    public void setOwner(String owner) { this.owner = owner; }
}

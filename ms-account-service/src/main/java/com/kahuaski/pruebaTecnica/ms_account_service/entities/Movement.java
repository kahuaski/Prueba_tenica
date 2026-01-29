package com.kahuaski.pruebaTecnica.ms_account_service.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "tbl_movements")
public class Movement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private String movementType;

    @Column(nullable = false)
    private BigDecimal amount = BigDecimal.ZERO;

    @Column(nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    public Movement() {}

    public Movement(LocalDateTime date, String movementType, BigDecimal amount, BigDecimal balance, Account account) {
        this.date = date != null ? date : LocalDateTime.now();
        this.movementType = movementType;
        this.amount = amount != null ? amount : BigDecimal.ZERO;
        this.balance = balance != null ? balance : BigDecimal.ZERO;
        this.account = account;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }
    public String getMovementType() { return movementType; }
    public void setMovementType(String movementType) { this.movementType = movementType; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }
    public Account getAccount() { return account; }
    public void setAccount(Account account) { this.account = account; }
}

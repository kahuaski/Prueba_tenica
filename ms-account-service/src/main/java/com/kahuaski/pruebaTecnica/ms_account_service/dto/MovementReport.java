package com.kahuaski.pruebaTecnica.ms_account_service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MovementReport {
    private LocalDateTime date;
    private String movementType;
    private BigDecimal amount;
    private BigDecimal balance;

    public MovementReport() {}

    public MovementReport(LocalDateTime date, String movementType, BigDecimal amount, BigDecimal balance) {
        this.date = date;
        this.movementType = movementType;
        this.amount = amount;
        this.balance = balance;
    }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }
    public String getMovementType() { return movementType; }
    public void setMovementType(String movementType) { this.movementType = movementType; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }
}

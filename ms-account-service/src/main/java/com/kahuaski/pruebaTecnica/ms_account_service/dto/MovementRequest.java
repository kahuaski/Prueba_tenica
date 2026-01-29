package com.kahuaski.pruebaTecnica.ms_account_service.dto;

import java.math.BigDecimal;

public class MovementRequest {
    private String accountNumber;
    private String movementType;
    private BigDecimal amount;

    public MovementRequest() {}

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }
    public String getMovementType() { return movementType; }
    public void setMovementType(String movementType) { this.movementType = movementType; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
}

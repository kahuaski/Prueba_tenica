package com.kahuaski.pruebaTecnica.ms_account_service.dto;

import java.util.List;

public class ReportResponse {
    private String cliente;
    private List<AccountReport> accounts;

    public ReportResponse() {}

    public ReportResponse(String cliente, List<AccountReport> accounts) {
        this.cliente = cliente;
        this.accounts = accounts;
    }

    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }
    public List<AccountReport> getAccounts() { return accounts; }
    public void setAccounts(List<AccountReport> accounts) { this.accounts = accounts; }
}

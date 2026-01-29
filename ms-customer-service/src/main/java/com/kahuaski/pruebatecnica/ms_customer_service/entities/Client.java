package com.kahuaski.pruebatecnica.ms_customer_service.entities;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tbl_clients")
public class Client extends Person {

    @Column(unique = true, nullable = false)
    private String clientId; 

    @Column(nullable = false)
    private String password;

    private Boolean status; 

    public Client() {
    }

    public Client(String clientId, String password, Boolean status) {
        this.clientId = clientId;
        this.password = password;
        this.status = status;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        if (!super.equals(o)) return false;
        Client client = (Client) o;
        return Objects.equals(clientId, client.clientId) &&
                Objects.equals(password, client.password) &&
                Objects.equals(status, client.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), clientId, password, status);
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId='" + clientId + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                '}';
    }
}
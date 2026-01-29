package com.kahuaski.pruebatecnica.ms_customer_service.services;

import com.kahuaski.pruebatecnica.ms_customer_service.entities.Client;
import java.util.List;

public interface ClientService {
    List<Client> findAll();
    Client findByClientId(String clientId);
    Client save(Client client);
    Client update(String clientId, Client clientDetails);
    void delete(String clientId);
}
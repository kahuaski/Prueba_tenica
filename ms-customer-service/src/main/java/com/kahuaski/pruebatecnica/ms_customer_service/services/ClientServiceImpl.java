package com.kahuaski.pruebatecnica.ms_customer_service.services;

import com.kahuaski.pruebatecnica.ms_customer_service.entities.Client;
import com.kahuaski.pruebatecnica.ms_customer_service.repositories.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Client findByClientId(String clientId) {
        return clientRepository.findByClientId(clientId)
                .orElseThrow(() -> new RuntimeException("Error: Cliente con ID " + clientId + " no existe."));
    }

    @Override
    @Transactional
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    @Transactional
    public Client update(String clientId, Client details) {
        Client existingClient = findByClientId(clientId);
        BeanUtils.copyProperties(details, existingClient, "id", "clientId");
        return clientRepository.save(existingClient);
    }

    @Override
    @Transactional
    public void delete(String clientId) {
        Client client = findByClientId(clientId);
        clientRepository.delete(client);
    }
}
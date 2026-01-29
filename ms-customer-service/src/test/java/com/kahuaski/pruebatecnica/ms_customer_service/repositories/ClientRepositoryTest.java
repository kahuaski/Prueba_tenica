package com.kahuaski.pruebatecnica.ms_customer_service.repositories;

import com.kahuaski.pruebatecnica.ms_customer_service.entities.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void whenSaveClient_thenFindById() {
        Client c = new Client();
        c.setClientId("test.client");
        c.setPassword("pwd");
        c.setStatus(true);

        Client saved = clientRepository.save(c);
        Assertions.assertNotNull(saved.getId());

        Client found = clientRepository.findByClientId("test.client").orElse(null);
        Assertions.assertNotNull(found);
        Assertions.assertEquals("test.client", found.getClientId());
    }
}

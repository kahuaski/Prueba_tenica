package com.kahuaski.pruebatecnica.ms_customer_service.repositories;

import com.kahuaski.pruebatecnica.ms_customer_service.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByClientId(String clientId);

}
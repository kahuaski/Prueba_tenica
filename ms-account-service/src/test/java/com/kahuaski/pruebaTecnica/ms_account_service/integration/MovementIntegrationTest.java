package com.kahuaski.pruebaTecnica.ms_account_service.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kahuaski.pruebaTecnica.ms_account_service.dto.MovementRequest;
import com.kahuaski.pruebaTecnica.ms_account_service.entities.Account;
import com.kahuaski.pruebaTecnica.ms_account_service.repositories.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MovementIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        accountRepository.deleteAll();
        Account a = new Account("INT-001", BigDecimal.valueOf(100), "Corriente", true, "Test User");
        accountRepository.save(a);
    }

    @Test
    public void whenWithdrawMoreThanBalance_thenReturn422AndSaldoNoDisponible() throws Exception {
        MovementRequest req = new MovementRequest();
        req.setAccountNumber("INT-001");
        req.setMovementType("Retiro");
        req.setAmount(BigDecimal.valueOf(-200));

        mockMvc.perform(post("/movimientos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Saldo no disponible")));
    }
}

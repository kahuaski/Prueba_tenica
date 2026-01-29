package com.kahuaski.pruebatecnica.ms_customer_service.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "ms-customer-service OK";
    }

}

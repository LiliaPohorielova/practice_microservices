package com.nixsolutions.fraud.controller;

import com.nixsolutions.clients.fraud.FraudCheckResponse;
import com.nixsolutions.fraud.service.FraudCheckService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/fraud-check")
public class FraudController {

    private final FraudCheckService fraudCheckService;

    @GetMapping("/{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable Integer customerId) {
        boolean isFraudster = fraudCheckService.isFraudulentCustomer(customerId);
        log.info("isFraudster check for customer {}", customerId);
        return new FraudCheckResponse(isFraudster);
    }
}

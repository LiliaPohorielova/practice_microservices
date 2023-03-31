package com.nixsolutions.fraud.controller;

import com.nixsolutions.fraud.dto.FraudCheckResponse;
import com.nixsolutions.fraud.service.FraudCheckService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fraud")
@AllArgsConstructor
public class FraudController {

    private final FraudCheckService fraudCheckService;

    @GetMapping("/{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable Integer customerId) {
        boolean isFraudster = fraudCheckService.isFraudulentCustomer(customerId);
        return new FraudCheckResponse(isFraudster);
    }
}

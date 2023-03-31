package com.nixsolutions.fraud.service;

import com.nixsolutions.fraud.entity.FraudCheckHistory;
import com.nixsolutions.fraud.repository.FraudCheckHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FraudCheckService {

    private final FraudCheckHistoryRepository fraudRepository;

    public boolean isFraudulentCustomer(Integer customerId) {
        fraudRepository.save(FraudCheckHistory.builder()
            .customerId(customerId)
            .isFraudster(false)
            .createdAt(LocalDateTime.now())
            .build());
        return false;
    }
}

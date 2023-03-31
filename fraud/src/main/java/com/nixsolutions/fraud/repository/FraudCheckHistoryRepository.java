package com.nixsolutions.fraud.repository;

import com.nixsolutions.fraud.entity.FraudCheckHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FraudCheckHistoryRepository
    extends JpaRepository<FraudCheckHistory, Integer> {
}

package com.api.bank.repository;

import com.api.bank.model.Credit;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CreditRepository extends MongoRepository<Credit, String> {
    Credit findCreditByNumber(String CreditNumber);
}

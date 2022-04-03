package com.api.bank.repository;

import com.api.bank.model.Credit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface CreditRepository extends MongoRepository<Credit, Serializable> {
    Credit findCreditByNumber(String CreditNumber);
}

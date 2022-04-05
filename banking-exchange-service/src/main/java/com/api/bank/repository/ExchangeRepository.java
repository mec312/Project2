package com.api.bank.repository;

import com.api.bank.model.Exchange;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface ExchangeRepository extends MongoRepository<Exchange, Serializable> {
    Exchange findByCode(String code);
}

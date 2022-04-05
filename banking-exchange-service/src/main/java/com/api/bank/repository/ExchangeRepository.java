package com.api.bank.repository;

import com.api.bank.model.Exchange;
import com.api.bank.request.ExchangeRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface ExchangeRepository extends MongoRepository<Exchange, Serializable> {
    Exchange findExchange(ExchangeRequest request);
}

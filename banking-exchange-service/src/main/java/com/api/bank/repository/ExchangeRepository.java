package com.api.bank.repository;

import com.api.bank.model.Exchange;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.io.Serializable;

public interface ExchangeRepository extends MongoRepository<Exchange, Serializable> {
}

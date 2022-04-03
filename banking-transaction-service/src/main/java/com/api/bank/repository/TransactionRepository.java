package com.api.bank.repository;

import com.api.bank.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.io.Serializable;

public interface TransactionRepository extends MongoRepository<Transaction, Serializable> {

}

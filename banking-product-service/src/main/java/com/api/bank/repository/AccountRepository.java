package com.api.bank.repository;

import com.api.bank.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface AccountRepository extends MongoRepository<Account, Serializable> {
    Account findAccountByNumber(String accountNumber);
}

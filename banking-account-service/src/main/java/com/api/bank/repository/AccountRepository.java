package com.api.bank.repository;

import com.api.bank.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, String> {
    Account findAccountByNumber(String accountNumber);
}

package com.api.bank.dao;

import com.api.bank.model.Account;
import io.reactivex.Maybe;
import org.springframework.stereotype.Component;

@Component
public interface AccountDao {
    Account findAccountByNumber(String accountNumber);
    Maybe<Account> updateAccount(Account acc);
    Maybe<Account> createAccount(Account acc);
}

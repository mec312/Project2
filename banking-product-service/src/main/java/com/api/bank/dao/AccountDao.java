package com.api.bank.dao;

import com.api.bank.model.Account;
import io.reactivex.Maybe;

public interface AccountDao {
    Maybe<Account> findAccountByNumber(String accountNumber);
    Maybe<Account> updateAccount(Account acc);
    Maybe<Account> createAccount(Account acc);
}

package com.api.bank.dao;

import com.api.bank.model.Account;

public interface AccountDao {
    Account findAccountByNumber(String accountNumber);
    Account createAccount(Account acc);
}

package com.api.bank.dao.impl;

import com.api.bank.dao.AccountDao;
import com.api.bank.model.Account;
import com.api.bank.model.User;
import com.api.bank.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountDaoImpl implements AccountDao {
    @Autowired
    private AccountRepository repository;

    @Override
    public Account findAccountByNumber(String accountNumber){
        return repository.findAccountByNumber(accountNumber);
    }

    private Account createAccount(Account acc, User uss){
        String uri = "http://localhost:8080/user/findUserbyDni";

        return repository.save(acc);
    }

}

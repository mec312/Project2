package com.api.bank.bussiness.impl;

import com.api.bank.bussiness.AccountService;
import com.api.bank.dao.AccountDao;
import com.api.bank.model.Account;
import com.api.bank.model.User;
import io.reactivex.Maybe;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private final AccountDao accountDao;

    @Override
    public Maybe<ResponseEntity<Account>> findAccountbyNumber(String accountNumber) {
        return accountDao.findAccountByNumber(accountNumber).map(acc -> validaRespuesta(acc));

    }

    public Maybe<ResponseEntity<Account>> updateAccount(Account acc) {
        return accountDao.updateAccount(acc).map(acct -> validaRespuesta(acct));
    }

    public Maybe<ResponseEntity<Account>> createAccount(Account acc) {
        return accountDao.createAccount(acc).map(acct -> validaRespuesta(acct));
    }

    private ResponseEntity<Account> validaRespuesta(Account acc) {
        return (acc != null)
                ? ResponseEntity.ok(acc)
                : ResponseEntity.noContent().build();
    }
}

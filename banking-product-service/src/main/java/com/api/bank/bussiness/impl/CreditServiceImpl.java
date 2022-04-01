package com.api.bank.bussiness.impl;

import com.api.bank.bussiness.CreditService;
import com.api.bank.dao.AccountDao;
import com.api.bank.model.Account;
import com.api.bank.model.Credit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CreditServiceImpl implements CreditService {
    @Autowired
    private final CreditDao accountDao;

    @Override
    public ResponseEntity<Mono<Account>> findAccountbyNumber(String accountNumber) {
        Account acc = accountDao.findAccountByNumber(accountNumber);
        return validaRespuesta(acc);
    }

    public ResponseEntity<Mono<Account>> createAccount(Account acc) {
        Account acct = accountDao.createAccount(acc);
        return validaRespuesta(acct);
    }

    private ResponseEntity<Mono<Account>> validaRespuesta(Account account) {
        return (account != null)
                ? ResponseEntity.ok(Mono.just(account)) : ResponseEntity.noContent().build();
    }

    private ResponseEntity<Flux<Account>> validaRespuesta(Flux<Account> account) {
        return (account != null)
                ? ResponseEntity.ok(account) : ResponseEntity.noContent().build();
    }
}

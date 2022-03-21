package com.api.bank.bussiness.impl;

import com.api.bank.bussiness.AccountService;
import com.api.bank.dao.AccountDao;
import com.api.bank.model.Account;
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
    public ResponseEntity<Mono<Account>> findAccountbyNumber(String accountNumber) {
        Account acc = accountDao.findAccountByNumber(accountNumber);
        return validaRespuesta(acc);
    }

    private ResponseEntity<Mono<Account>> validaRespuesta(Account account) {
        return (account!=null)
                ? ResponseEntity.ok(Mono.just(account)):ResponseEntity.noContent().build();
    }

    private ResponseEntity<Flux<Account>> validaRespuesta(Flux<Account> account) {
        return (account!=null)
                ? ResponseEntity.ok(account):ResponseEntity.noContent().build();
    }
}

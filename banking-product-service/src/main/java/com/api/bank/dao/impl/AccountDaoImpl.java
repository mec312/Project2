package com.api.bank.dao.impl;

import com.api.bank.dao.AccountDao;
import com.api.bank.exception.ValidationException;
import com.api.bank.internal.AccountStatus;
import com.api.bank.model.Account;
import com.api.bank.model.User;
import com.api.bank.repository.AccountRepository;
import io.reactivex.Maybe;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AccountDaoImpl implements AccountDao {
    @Autowired
    private AccountRepository repository;

    @Override
    @Transactional
    @Cacheable(value = "account", key = "#accountNumber")
    public Account findAccountByNumber(String accountNumber) {
        return repository.findAccountByNumber(accountNumber);
    }

    @Override
    public Maybe<Account> updateAccount(Account acc) {
        Optional.ofNullable(repository.findAccountByNumber(acc.getNumber()))
                .ifPresentOrElse(account -> {
                    account.setStatus(acc.getStatus());
                    account.setActualBalance(acc.getActualBalance());
                    account.setAvailableBalance(acc.getAvailableBalance());
                    repository.save(account);
                        },
                        () -> {
                    throw new ValidationException("La cuenta no fue encontrada");
                });
        return repository.findAccountByNumber(acc.getNumber()) != null
                ? Maybe.just(acc)
                : Maybe.empty();
    }

    @Override
    public Maybe<Account> createAccount(Account acc) {
        Optional.ofNullable(repository.findAccountByNumber(acc.getNumber()))
                .ifPresentOrElse(u -> {
                    throw new ValidationException("La cuenta ya existe");
                }, () -> {
                    String uri = "http://localhost:8084/user/findUserbyDni?dni=" + acc.getUserDni();
                    RestTemplate restT = new RestTemplate();
                    User ussRes = restT.getForObject(uri, User.class, User.class);

                    Optional.ofNullable(ussRes).ifPresent(uss -> {
                        Maybe.just(repository.save(acc));
                    });
                });


        return Maybe.just(acc);

    }

}

package com.api.bank.dao.impl;

import com.api.bank.dao.AccountDao;
import com.api.bank.model.Account;
import com.api.bank.model.User;
import com.api.bank.repository.AccountRepository;
import io.reactivex.Maybe;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.validation.ValidationException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AccountDaoImpl implements AccountDao {
    @Autowired
    private AccountRepository repository;

    @Override
    public Maybe<Account> findAccountByNumber(String accountNumber) {
        return repository.findAccountByNumber(accountNumber) != null
                ? Maybe.just(repository.findAccountByNumber(accountNumber))
                : Maybe.empty();

    }

    public Maybe<Account> createAccount(Account acc) {
        /*Revisar*/
        Optional.ofNullable(repository.findAccountByNumber(acc.getNumber()))
                .ifPresent(u -> {
                    throw new ValidationException("La cuenta ya existe");
                });

        String uri = "http://localhost:8084/user/findUserbyDni?dni=" + acc.getUser().getDni();
        RestTemplate restT = new RestTemplate();
        User ussRes = restT.getForObject(uri, User.class, User.class);

        Optional.ofNullable(ussRes).ifPresent(uss -> {
            Maybe.just(repository.save(acc));
        });
        return Maybe.just(acc);

        /*
        if (ussRes != null)
            Maybe.just(repository.save(acc));
        return Maybe.just(acc);
        */
    }

}

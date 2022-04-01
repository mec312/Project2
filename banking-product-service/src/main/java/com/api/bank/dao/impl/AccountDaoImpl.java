package com.api.bank.dao.impl;

import com.api.bank.dao.AccountDao;
import com.api.bank.model.Account;
import com.api.bank.model.User;
import com.api.bank.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class AccountDaoImpl implements AccountDao {
    @Autowired
    private AccountRepository repository;

    @Override
    public Account findAccountByNumber(String accountNumber) {
        return repository.findAccountByNumber(accountNumber);
    }

    public Account createAccount(Account acc) {
        String dni = acc.getUser().getDni();
        String uri = "http://localhost:8084/user/findUserbyDni?dni=" + dni;
        RestTemplate restTemplate = new RestTemplate();
        User ussRes = restTemplate.getForObject(uri, User.class, User.class);
        if (ussRes != null) {
            repository.save(acc);
        }
        return acc;
    }

}

package com.api.bank.dao.impl;

import com.api.bank.dao.CreditDao;
import com.api.bank.model.Account;
import com.api.bank.model.Credit;
import com.api.bank.model.User;
import com.api.bank.repository.AccountRepository;
import com.api.bank.repository.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class CreditDaoImpl implements CreditDao {

    @Autowired
    private CreditRepository repository;

    @Override
    public Credit findCreditByNumber(String creditNumber) {
        return repository.findCreditByNumber(creditNumber);
    }

    public Credit createCredit(Credit cre) {
        String credNumber =
        String uri = "http://localhost:8084/user/findUserbyDni?dni=" + dni;
        RestTemplate restTemplate = new RestTemplate();
        User ussRes = restTemplate.getForObject(uri, User.class, User.class);
        if (ussRes != null) {
            repository.save(acc);
        }
        return acc;
    }
}

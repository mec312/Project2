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
        String credNumber = cre.getNumber();
        String uri = "http://localhost:8085/product/credit/findCreditbyNumber?creditNumber=" + credNumber;
        RestTemplate restTemplate = new RestTemplate();
        Credit credRes = restTemplate.getForObject(uri, Credit.class, Credit.class);
        if (credRes != null) {
            repository.save(cre);
        }
        return cre;
    }
}

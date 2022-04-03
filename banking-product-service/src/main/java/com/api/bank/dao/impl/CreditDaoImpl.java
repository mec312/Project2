package com.api.bank.dao.impl;

import com.api.bank.dao.CreditDao;
import com.api.bank.model.Account;
import com.api.bank.model.Credit;
import com.api.bank.model.User;
import com.api.bank.repository.AccountRepository;
import com.api.bank.repository.CreditRepository;
import io.reactivex.Maybe;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.validation.ValidationException;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CreditDaoImpl implements CreditDao {

    @Autowired
    private CreditRepository repository;

    @Override
    @Transactional
    @Cacheable(value = "credit", key = "#creditNumber")
    public Credit findCreditByNumber(String creditNumber) {
        return repository.findCreditByNumber(creditNumber);
    }

    public Maybe<Credit> createCredit(Credit cre) {
        Optional.ofNullable(repository.findCreditByNumber(cre.getNumber()))
                .ifPresent(c->{
                    throw new ValidationException("La cuenta ya existe");
                });
        return Maybe.just(repository.save(cre));

    }
}

package com.api.bank.dao.impl;

import com.api.bank.dao.ExchangeDao;
import com.api.bank.exception.ValidationException;
import com.api.bank.model.Exchange;
import com.api.bank.repository.ExchangeRepository;
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
public class ExchangeDaoimpl implements ExchangeDao {

    @Autowired
    private ExchangeRepository repository;

    @Override
    public Maybe<Exchange> createExchange(Exchange request){



        Optional.ofNullable(repository.findByCode(request.getCode()))
                .ifPresentOrElse(u -> {
                    throw new ValidationException("Exchange ya existe");
                }, () -> {
                    Maybe.just(repository.save(request));
                });

        return Maybe.just(request);
    }

    @Override
    @Transactional
    @Cacheable(value = "exchange", key = "#code")
    public Exchange findExchange(String code){
        return repository.findByCode(code);
    }
}

package com.api.bank.dao.impl;

import com.api.bank.dao.ExchangeDao;
import com.api.bank.model.Exchange;
import com.api.bank.repository.ExchangeRepository;
import com.api.bank.request.ExchangeRequest;
import io.reactivex.Maybe;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ExchangeDaoimpl implements ExchangeDao {

    @Autowired
    private ExchangeRepository repository;

    @Override
    public Maybe<Exchange> createExchange(Exchange request){
        return null;
    }

    @Override
    @Transactional
    @Cacheable(value = "exchange", key = "#id")
    public Exchange findExchange(ExchangeRequest request){
        return null;
    }
}

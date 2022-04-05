package com.api.bank.bussiness.impl;

import com.api.bank.bussiness.ExchangeService;
import com.api.bank.dao.ExchangeDao;
import com.api.bank.model.Exchange;
import com.api.bank.request.ExchangeRequest;
import io.reactivex.Maybe;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ExchangeServiceImpl implements ExchangeService {

    @Autowired
    private final ExchangeDao exchangeDao;

    @Override
    public Maybe<ResponseEntity<Exchange>> createExchange(Exchange request){
        return null;
    }

    @Override
    public Maybe<ResponseEntity<Exchange>> findExchange(ExchangeRequest request){
        return null;
    }
}

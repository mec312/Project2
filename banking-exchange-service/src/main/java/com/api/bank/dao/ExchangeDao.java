package com.api.bank.dao;

import com.api.bank.model.Exchange;
import com.api.bank.request.ExchangeRequest;
import io.reactivex.Maybe;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface ExchangeDao {
    Maybe<Exchange> createExchange(Exchange request);
    Exchange findExchange(ExchangeRequest request);
}

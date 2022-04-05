package com.api.bank.bussiness;

import com.api.bank.model.Exchange;
import com.api.bank.request.ExchangeRequest;
import io.reactivex.Maybe;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface ExchangeService {
    Maybe<ResponseEntity<Exchange>> createExchange(Exchange request);
    Maybe<ResponseEntity<Exchange>> findExchange(ExchangeRequest request);

}

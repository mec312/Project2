package com.api.bank.bussiness;

import com.api.bank.model.Account;
import com.api.bank.model.Credit;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface CreditService {
    ResponseEntity<Mono<Credit>> findCreditByNumber(String creditNumber);

    ResponseEntity<Mono<Credit>> createCredit(Credit cred);
}

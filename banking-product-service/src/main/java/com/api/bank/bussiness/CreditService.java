package com.api.bank.bussiness;

import com.api.bank.model.Account;
import com.api.bank.model.Credit;
import io.reactivex.Maybe;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public interface CreditService {
    Maybe<ResponseEntity<Credit>> findCreditByNumber(String creditNumber);
    Maybe<ResponseEntity<Credit>> createCredit(Credit cred);
}

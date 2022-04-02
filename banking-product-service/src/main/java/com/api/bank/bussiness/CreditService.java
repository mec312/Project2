package com.api.bank.bussiness;

import com.api.bank.model.Account;
import com.api.bank.model.Credit;
import io.reactivex.Maybe;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface CreditService {
    Maybe<ResponseEntity<Credit>> findCreditByNumber(String creditNumber);

    Maybe<ResponseEntity<Credit>> createCredit(Credit cred);
}

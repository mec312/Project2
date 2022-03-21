package com.api.bank.bussiness;

import com.api.bank.model.Account;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface AccountService {
    ResponseEntity<Mono<Account>> findAccountbyNumber(String accountNumber);
}

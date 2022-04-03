package com.api.bank.bussiness;

import com.api.bank.model.Account;
import com.api.bank.model.User;
import io.reactivex.Maybe;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public interface AccountService {
    Maybe<ResponseEntity<Account>> findAccountbyNumber(String accountNumber);
    Maybe<ResponseEntity<Account>> updateAccount(Account acc);
    Maybe<ResponseEntity<Account>> createAccount(Account acc);
}

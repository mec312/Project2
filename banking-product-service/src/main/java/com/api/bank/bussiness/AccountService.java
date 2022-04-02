package com.api.bank.bussiness;

import com.api.bank.model.Account;
import com.api.bank.model.User;
import io.reactivex.Maybe;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface AccountService {
    Maybe<ResponseEntity<Account>> findAccountbyNumber(String accountNumber);

    Maybe<ResponseEntity<Account>> createAccount(Account acc);
}

package com.api.bank.bussiness;

import com.api.bank.internal.UserStatus;
import com.api.bank.model.User;
import io.reactivex.Maybe;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public interface UserService {
    Maybe<ResponseEntity<User>> createUser(User uss);

    Maybe<ResponseEntity<User>> findByDni(String dni);

    Maybe<ResponseEntity<User>> updateUser(String dni, UserStatus sta);
}

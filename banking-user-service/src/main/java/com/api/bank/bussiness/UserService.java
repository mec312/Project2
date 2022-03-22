package com.api.bank.bussiness;

import com.api.bank.internal.UserStatus;
import com.api.bank.model.User;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface UserService {
    ResponseEntity<Mono<User>> createUser(User uss);

    ResponseEntity<Mono<User>> findByDni(String dni);

    ResponseEntity<Mono<User>> updateUser(String dni, UserStatus sta);
}

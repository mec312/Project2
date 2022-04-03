package com.api.bank.dao;


import com.api.bank.internal.UserStatus;
import com.api.bank.model.User;
import io.reactivex.Maybe;
import io.reactivex.Single;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public interface UserDao {
    User createUser(User uss);

    User findByDni(String dni);

    User updateUser(String dni, UserStatus sta);

}

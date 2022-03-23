package com.api.bank.dao;


import com.api.bank.internal.UserStatus;
import com.api.bank.model.User;
import io.reactivex.Maybe;
import io.reactivex.Single;

public interface UserDao {
    Maybe<User> createUser(User uss);

    Maybe<User> findByDni(String dni);

    Maybe<User> updateUser(String dni, UserStatus sta);

}

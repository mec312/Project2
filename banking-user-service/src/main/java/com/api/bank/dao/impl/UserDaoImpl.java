package com.api.bank.dao.impl;

import com.api.bank.dao.UserDao;
import com.api.bank.internal.UserStatus;
import com.api.bank.model.User;
import com.api.bank.repository.UserRepository;
import io.reactivex.Maybe;
import io.reactivex.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDaoImpl implements UserDao {

    @Autowired
    private UserRepository repository;

    @Override
    public Maybe<User> createUser(User uss){
        /*Correcion*/
      return repository.findByDni(uss.getDni()) == null
                ? Maybe.just(repository.save(uss))
                : Maybe.empty();
    }

    @Override
    public Maybe<User> findByDni(String dni) {
        return repository.findByDni(dni) != null
                ? Maybe.just(repository.findByDni(dni))
                : Maybe.empty();

        //return Maybe.just(repository.findByDni(dni));
                //.switchIfEmpty(Maybe.just(new User()));

    }

    @Override
    public Maybe<User> updateUser(String dni, UserStatus sta){
        return findByDni(dni)
                .map(user -> {
                    user.setUserStatus(sta);
                    repository.save(user);
                    return user;
                });

    }


}


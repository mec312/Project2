package com.api.bank.dao.impl;

import com.api.bank.dao.UserDao;
import com.api.bank.exception.ValidationException;
import com.api.bank.internal.UserStatus;
import com.api.bank.model.User;
import com.api.bank.repository.UserRepository;
import io.reactivex.Maybe;
import io.reactivex.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserDaoImpl implements UserDao {

    @Autowired
    private UserRepository repository;

    @Override
    public User createUser(User uss){
        Optional.ofNullable(repository.findByDni(uss.getDni()))
                .ifPresent(u -> {
                    throw new ValidationException("El usuario ya existe");
                });
        return repository.save(uss);
    }

    @Override
    @Transactional
    @Cacheable(value = "user", key = "#dni")
    public User findByDni(String dni) {
        return repository.findByDni(dni);
    }

    public User updateUser(String dni, UserStatus sta){
        User us = findByDni(dni);
        us.setUserStatus(sta);
        repository.save(us);
        return us;
    }
}


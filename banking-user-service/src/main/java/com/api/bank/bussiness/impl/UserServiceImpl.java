package com.api.bank.bussiness.impl;

import com.api.bank.bussiness.UserService;
import com.api.bank.dao.UserDao;
import com.api.bank.internal.UserStatus;
import com.api.bank.model.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public ResponseEntity<Mono<User>> createUser(User uss){

            return validaRespuesta(userDao.createUser(uss));

    }

    @Override
    public ResponseEntity<Mono<User>> findByDni(String dni){

        return validaRespuesta(userDao.findByDni(dni));
    }

    @Override
    public ResponseEntity<Mono<User>> updateUser(String dni, UserStatus sta){
        return validaRespuesta(userDao.updateUser(dni,sta));
    }

    private ResponseEntity<Mono<User>> validaRespuesta(User user) {
        return (user!=null)
                ? ResponseEntity.ok(Mono.just(user))
                : ResponseEntity.noContent().build();
    }
}
package com.api.bank.bussiness.impl;

import com.api.bank.bussiness.UserService;
import com.api.bank.dao.UserDao;
import com.api.bank.internal.UserStatus;
import com.api.bank.model.User;
import io.reactivex.Maybe;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
@Log4j2
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Maybe<ResponseEntity<User>> createUser(User uss) {
        return userDao.createUser(uss)
                .map(user -> validaRespuesta(user));
        /*
        return userDao.findByDni(uss.getDni()).map(use -> {
            if (use.getDni().isEmpty()) {
                userDao.createUser(uss)
                        .map(user -> validaRespuesta(user));
            }
            return use;
        }).map(ResponseEntity::ok);
        */
    }


    @Override
    public Maybe<ResponseEntity<User>> findByDni(String dni){
      return userDao.findByDni(dni)
              .map(user -> validaRespuesta(user));
    }

    @Override
    public Maybe<ResponseEntity<User>> updateUser(String dni, UserStatus sta){
        return userDao.updateUser(dni, sta)
                .map(user-> validaRespuesta(user));

    }

    private ResponseEntity<User> validaRespuesta(User user) {
        return (user!=null)
                ? ResponseEntity.ok(user)
                : ResponseEntity.noContent().build();
    }

}

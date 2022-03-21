package com.api.bank.dao.impl;

import com.api.bank.dao.UserDao;
import com.api.bank.internal.Status;
import com.api.bank.model.User;
import com.api.bank.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
@RequiredArgsConstructor
@Service
public class UserDaoImpl implements UserDao {

    @Autowired
    private UserRepository repository;

    @Override
    public User createUser(User uss){
        return repository.save(uss);
    }

    @Override
    public User findByDni(String dni) {

        return repository.findByDni(dni);
    }

    @Override
    public User updateUser(String dni, Status sta){
        User uss = findByDni(dni);
        uss.setStatus(sta);
        repository.save(uss);
        return uss;
    }


}


package com.api.bank.dao.impl;

import com.api.bank.dao.UserDao;
import com.api.bank.internal.UserStatus;
import com.api.bank.model.User;
import com.api.bank.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public User updateUser(String dni, UserStatus sta){
        User uss = findByDni(dni);
        uss.setUserStatus(sta);
        repository.save(uss);
        return uss;
    }


}


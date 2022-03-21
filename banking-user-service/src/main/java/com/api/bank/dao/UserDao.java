package com.api.bank.dao;


import com.api.bank.internal.Status;
import com.api.bank.model.User;

public interface UserDao {
    User createUser(User uss);

    User findByDni(String dni);

    User updateUser(String dni, Status sta);

}

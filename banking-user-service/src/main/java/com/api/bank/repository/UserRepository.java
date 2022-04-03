package com.api.bank.repository;

import com.api.bank.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface UserRepository extends MongoRepository<User, Serializable> {
    User findByDni(String dni);
}

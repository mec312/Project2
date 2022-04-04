package com.api.bank.dao.impl;

import com.api.bank.dao.YankiDao;
import com.api.bank.model.Transaction;
import com.api.bank.model.User;

import java.math.BigDecimal;

public class YankiDaoImpl implements YankiDao {

    @Override
    public User createUserYanki(User uss){
        return null;
    }

    @Override
    public Transaction FundTransferYanki(String fromAccount, String toAccount, BigDecimal amount){
        return null;
    }

    @Override
    public Transaction PayYanki(String fromAccount, BigDecimal amount){
        return null;
    }

    @Override
    public Transaction MembershipYanki(String fromAccount, BigDecimal amount){
        return null;
    }
}

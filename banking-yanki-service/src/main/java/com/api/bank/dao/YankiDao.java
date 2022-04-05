package com.api.bank.dao;

import com.api.bank.model.Transaction;
import com.api.bank.model.User;

import java.math.BigDecimal;

public interface YankiDao {
    User createUserYanki(User uss);
    Transaction FundTransferYanki(String fromAccount, String toAccount, BigDecimal amount);
    Transaction PayYanki(String fromAccount, BigDecimal amount);
    Transaction MembershipYanki(String YankiAccount, String ChosenAccount);
    Transaction BuyYankiBootCoin(String fromAccount , BigDecimal amount);
}

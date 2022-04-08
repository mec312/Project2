package com.api.bank.dao;

import com.api.bank.model.Transaction;

import java.math.BigDecimal;

public interface TransactionDao {
    Transaction FundTransferTransaction(String fromAccount, String toAccount, BigDecimal amount);

    Transaction PayTransaction(BigDecimal amount,String account);

    Transaction buyBootCoin(BigDecimal amount,String account);
}

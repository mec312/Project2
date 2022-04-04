package com.api.bank.dao.impl;

import com.api.bank.dao.YankiDao;
import com.api.bank.internal.AccountStatus;
import com.api.bank.internal.AccountType;
import com.api.bank.internal.TransactionType;
import com.api.bank.model.Account;
import com.api.bank.model.Transaction;
import com.api.bank.model.User;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

public class YankiDaoImpl implements YankiDao {

    @Override
    public User createUserYanki(User uss){
        RestTemplate restT = new RestTemplate();
        String uri ="http://localhost:8084/user/regUser";
        User userYanki  = restT.postForObject(uri,uss, User.class);
        User uYanki = User.builder().dni(userYanki.getDni())
                .cellphoneImei(userYanki.getCellphoneImei())
                .cellphoneNumber(userYanki.getCellphoneNumber())
                .email(userYanki.getEmail()).build();

        String uri2 ="http://localhost:8085/product/account/regAccount";
        Account accY = Account.builder()
                .number(uss.getCellphoneNumber())
                .type(AccountType.YANKI)
                .actualBalance(new BigDecimal("0"))
                .availableBalance(new BigDecimal("0"))
                .userDni(uss.getDni())
                .status(AccountStatus.ACTIVE)
                .build();
        Account accYanki = restT.postForObject(uri2, accY, Account.class);
        return uYanki;
    }

    @Override
    public Transaction FundTransferYanki(String fromAccount, String toAccount, BigDecimal amount){
        RestTemplate restT = new RestTemplate();
        String uri ="http://localhost:8086/transaction/fundTransfer";
        Transaction trx = Transaction.builder()
                .amount(amount)
                .fromAccount(fromAccount)
                .toAccount(toAccount)
                .transactionType(TransactionType.FUNDTRANSFER_Y)
                .build();
        Transaction trxYanki  = restT.postForObject(uri, trx, Transaction.class);
        return trxYanki;
    }

    @Override
    public Transaction PayYanki(String fromAccount, BigDecimal amount){
        RestTemplate restT = new RestTemplate();
        String uri ="http://localhost:8086/transaction/payment";
        Transaction trx = Transaction.builder()
                .amount(amount)
                .fromAccount(fromAccount)
                .transactionType(TransactionType.UTILITYPAYMENT_Y)
                .build();
        Transaction trxYanki  = restT.postForObject(uri, trx, Transaction.class);
        return trxYanki;
    }

    @Override
    public Transaction MembershipYanki(String fromAccount, BigDecimal amount){
        return null;
    }
}

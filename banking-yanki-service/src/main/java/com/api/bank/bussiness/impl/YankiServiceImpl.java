package com.api.bank.bussiness.impl;

import com.api.bank.bussiness.YankiService;
import com.api.bank.dao.YankiDao;
import com.api.bank.model.Transaction;
import com.api.bank.model.User;
import io.reactivex.Maybe;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class YankiServiceImpl implements YankiService {

    @Autowired
    private final YankiDao yankiDao;

    @Override
    public Maybe<ResponseEntity<User>> createUserYanki(User uss){
        return Maybe.just(validaRespuesta(yankiDao.createUserYanki(uss)));
    }

    @Override
    public Maybe<ResponseEntity<Transaction>> FundTransferYanki(String fromAccount, String toAccount, BigDecimal amount) {
        return Maybe.just(validaRespuesta(yankiDao.FundTransferYanki(fromAccount,toAccount,amount)));
    }

    @Override
    public Maybe<ResponseEntity<Transaction>> PayYanki(String fromAccount, BigDecimal amount){
        return Maybe.just(validaRespuesta(yankiDao.PayYanki(fromAccount,amount)));
    }

    @Override
    public Maybe<ResponseEntity<Transaction>> MembershipYanki(String YankiAccount, String ChosenAccount){
        return Maybe.just(validaRespuesta(yankiDao.MembershipYanki(YankiAccount, ChosenAccount)));
    }

    @Override
    public Maybe<ResponseEntity<Transaction>> BuyYankiBootCoin(String fromAccount, BigDecimal amount){
        return Maybe.just(validaRespuesta(yankiDao.BuyYankiBootCoin(fromAccount,amount)));
    }

    private ResponseEntity<User> validaRespuesta(User uss) {
        return (uss!=null)
                ? ResponseEntity.ok(uss)
                : ResponseEntity.noContent().build();
    }
    private ResponseEntity<Transaction> validaRespuesta(Transaction trx) {
        return (trx!=null)
                ? ResponseEntity.ok(trx)
                : ResponseEntity.noContent().build();
    }

}

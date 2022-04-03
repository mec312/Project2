package com.api.bank.bussiness.impl;

import com.api.bank.bussiness.TransactionService;
import com.api.bank.dao.TransactionDao;
import com.api.bank.model.Transaction;
import com.api.bank.model.User;
import io.reactivex.Maybe;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private final TransactionDao transactionDao;

    @Override
    public Maybe<ResponseEntity<Transaction>> PayTransaction(BigDecimal amount, String account){
        return Maybe.just(validaRespuesta(transactionDao.PayTransaction(amount,account)));
    }

    @Override
    public Maybe<ResponseEntity<Transaction>> FundTransferTransaction(String fromAccount, String toAccount, BigDecimal amount) {
        return Maybe.just(validaRespuesta(transactionDao.FundTransferTransaction(fromAccount,toAccount,amount)));
    }

    private ResponseEntity<Transaction> validaRespuesta(Transaction trx) {
        return (trx!=null)
                ? ResponseEntity.ok(trx)
                : ResponseEntity.noContent().build();
    }


}

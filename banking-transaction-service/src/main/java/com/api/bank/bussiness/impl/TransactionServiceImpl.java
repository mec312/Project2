package com.api.bank.bussiness.impl;

import com.api.bank.bussiness.TransactionService;
import com.api.bank.dao.TransactionDao;
import com.api.bank.model.Transaction;
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
    public ResponseEntity<Mono<Transaction>> PayTransaction(BigDecimal amount, String account){
        Transaction trx = transactionDao.PayTransaction(amount,account);
        return validaRespuesta(trx);
    }

    @Override
    public ResponseEntity<Mono<Transaction>> FundTransferTransaction(String fromAccount, String toAccount, BigDecimal amount) {
        Transaction trx = transactionDao.FundTransferTransaction(fromAccount,toAccount,amount);
        return validaRespuesta(trx);
    }

    private ResponseEntity<Mono<Transaction>> validaRespuesta(Transaction trx) {
        return (trx!=null)
                ? ResponseEntity.ok(Mono.just(trx))
                : ResponseEntity.noContent().build();
    }

    private ResponseEntity<Flux<Transaction>> validaRespuesta(Flux<Transaction> trx) {
        return (trx!=null)
                ? ResponseEntity.ok(trx)	: ResponseEntity.noContent().build();
    }

}

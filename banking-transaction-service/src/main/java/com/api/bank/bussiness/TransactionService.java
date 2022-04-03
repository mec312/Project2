package com.api.bank.bussiness;

import com.api.bank.model.Transaction;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface TransactionService {
    Mono<ResponseEntity<Transaction>> FundTransferTransaction(String fromAccount, String toAccount, BigDecimal amount);

    Mono<ResponseEntity<Transaction>> PayTransaction(BigDecimal amount,String account);
}

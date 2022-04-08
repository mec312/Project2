package com.api.bank.bussiness;

import com.api.bank.model.Transaction;
import io.reactivex.Maybe;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface TransactionService {
    Maybe<ResponseEntity<Transaction>> FundTransferTransaction(String fromAccount, String toAccount, BigDecimal amount);

    Maybe<ResponseEntity<Transaction>> PayTransaction(BigDecimal amount,String account);

    Maybe<ResponseEntity<Transaction>> buyBootCoin(BigDecimal amount,String account);

}

package com.api.bank.dao.impl;

import com.api.bank.dao.TransactionDao;
import com.api.bank.internal.TransactionType;
import com.api.bank.model.Account;
import com.api.bank.model.Transaction;
import com.api.bank.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.validation.ValidationException;
import java.math.BigDecimal;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class TransactionDaoImpl implements TransactionDao {

    @Autowired
    private TransactionRepository repositoryTra;

    //tranferir fondos
    @Override
    public Transaction FundTransferTransaction(String fromAccount, String toAccount, BigDecimal amount){
        RestTemplate restT = new RestTemplate();
        String uri ="http://localhost:8085/product/account/findAccountByNumber?accountNumber=";
        Account fromAcc = restT.getForObject(uri+fromAccount,Account.class, Account.class);
        Account toAcc = restT.getForObject(uri+toAccount,Account.class, Account.class);

        //validando balance de cuenta
        validateBalance(fromAcc, amount);
        Transaction transactionId = internalFundTransfer(fromAcc, toAcc, amount);
        return transactionId;
    }

    //pagar
    @Override
    public Transaction PayTransaction(BigDecimal amount,String account){
        String trxId = UUID.randomUUID().toString();//cambiar
        RestTemplate restT = new RestTemplate();
        String uri ="http://localhost:8085/product/account/findAccountByNumber?accountNumber=";
        Account fromAcc = restT.getForObject(uri+account,Account.class, Account.class);

        /*validando balance de cuenta*/
        validateBalance(fromAcc, amount);
        /*Seteando nuevos valores*/
        fromAcc.setActualBalance(fromAcc.getActualBalance().subtract(amount));
        fromAcc.setAvailableBalance(fromAcc.getActualBalance().subtract(amount));
        /*Actualizar Saldo en cuenta- aun sin implementar*/

        String restUrl = "http://localhost:8085/product/account/updateAccount";
        Mono<Account> fromAccMono = Mono.just(fromAcc);
        Mono<Account> resp = WebClient.create().post()
                .uri(restUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromAccMono,Account.class)
                .retrieve().bodyToMono(Account.class);//respuesta del post

        /*Generando transaccion*/
        Transaction trx = repositoryTra.save(Transaction.builder().transactionType(TransactionType.UTILITY_PAYMENT).fromAccount(fromAcc).amount(amount.negate()).build());
        return trx;

    }

    private Transaction internalFundTransfer(Account fromAccount, Account toAccount, BigDecimal amount){
        String trxId = UUID.randomUUID().toString();//cambiar

        fromAccount.setActualBalance(fromAccount.getActualBalance().subtract(amount));
        fromAccount.setAvailableBalance(fromAccount.getActualBalance().subtract(amount));

        toAccount.setActualBalance(toAccount.getActualBalance().add(amount));
        toAccount.setAvailableBalance(toAccount.getActualBalance().add(amount));
        /*Actualizar Saldo en cuenta- aun sin implementar -1*/

        Mono<Account> fromAccMono = Mono.just(fromAccount);
        Mono<Account> toAccMono = Mono.just(toAccount);

        log.info("Creating resp1 with {}", fromAccount.getActualBalance().toString());
        log.info("Creating resp2 with {}", toAccount.getActualBalance().toString());

        WebClient webClient = WebClient.create("http://localhost:8085/product");

        Mono<Account> resp1 = webClient.post()
                .uri("/account/updateAccount")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(fromAccMono,Account.class)
                .retrieve()
                .bodyToMono(Account.class);//respuesta del post

        Mono<Account> resp2 = webClient.post()
                .uri("/account/updateAccount")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(toAccMono,Account.class)
                .retrieve()
                .bodyToMono(Account.class);//respuesta del post

        /*Generando transaccion -1*/
        repositoryTra.save(Transaction.builder()
                .transactionType(TransactionType.FUND_TRANSFER)
                .fromAccount(fromAccount)
                .toAccount(toAccount)
                .transactionId(trxId)
                .amount(amount.negate()).build());

        repositoryTra.save(Transaction.builder()
                .transactionType(TransactionType.FUND_TRANSFER)
                .fromAccount(fromAccount)
                .toAccount(toAccount)
                .transactionId(trxId)
                .amount(amount).build());
        Transaction trx = Transaction.builder().transactionId(trxId).amount(amount).transactionType(TransactionType.FUND_TRANSFER).build();
        return trx;

    }

    private void validateBalance(Account account, BigDecimal amount){
        if (account.getActualBalance().compareTo(BigDecimal.ZERO) < 0 || account.getActualBalance().compareTo(amount) < 0) {
            throw new ValidationException("Saldo muy bajo para efectuar transaccion");
        }
    }


}

package com.api.bank.dao.impl;

import com.api.bank.dao.TransactionDao;
import com.api.bank.exception.ValidationException;
import com.api.bank.internal.AccountStatus;
import com.api.bank.internal.AccountType;
import com.api.bank.internal.TransactionType;
import com.api.bank.model.Account;
import com.api.bank.model.Transaction;
import com.api.bank.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


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
        fromAcc.setAvailableBalance(fromAcc.getAvailableBalance().subtract(amount));
        /*ANTIGUO - Actualizar Saldo en cuenta- FUNCIONA */
        String uri2 ="http://localhost:8085/product/account/updAccount";
        Account fromAc = restT.postForObject(uri2,fromAcc, Account.class);

        /*Generando transaccion*/
        Transaction trx = repositoryTra.save(Transaction.builder().transactionId(trxId).transactionType(TransactionType.UTILITY_PAYMENT).fromAccount(fromAcc.getNumber()).amount(amount.negate()).build());
        return trx;

    }

    private Transaction internalFundTransfer(Account fromAccount, Account toAccount, BigDecimal amount){
        String trxId = UUID.randomUUID().toString();//cambiar

        fromAccount.setActualBalance(fromAccount.getActualBalance().subtract(amount));
        fromAccount.setAvailableBalance(fromAccount.getAvailableBalance().subtract(amount));

        toAccount.setActualBalance(toAccount.getActualBalance().add(amount));
        toAccount.setAvailableBalance(toAccount.getAvailableBalance().add(amount));

        /*ANTIGUO - Actualizar Saldo en cuenta- FUNCIONA */
        RestTemplate restT = new RestTemplate();
        String uri ="http://localhost:8085/product/account/updAccount";
        Account fromAcc = restT.postForObject(uri,fromAccount, Account.class);
        Account toAcc = restT.postForObject(uri,toAccount, Account.class);

        /*Generando transaccion -1*/
        repositoryTra.save(Transaction.builder()
                .transactionType(TransactionType.FUND_TRANSFER)
                .fromAccount(fromAccount.getNumber())
                .toAccount(toAccount.getNumber())
                .transactionId(trxId)
                .amount(amount.negate()).build());

        repositoryTra.save(Transaction.builder()
                .transactionType(TransactionType.FUND_TRANSFER)
                .fromAccount(fromAccount.getNumber())
                .toAccount(toAccount.getNumber())
                .transactionId(trxId)
                .amount(amount).build());
        Transaction trx = Transaction.builder().transactionId(trxId).amount(amount).transactionType(TransactionType.FUND_TRANSFER).toAccount(toAccount.getNumber()).fromAccount(fromAccount.getNumber()).build();
        return trx;

    }

    @Override
    public Transaction buyBootCoin(BigDecimal amount,String account){
        String trxId = UUID.randomUUID().toString();//cambiar

        RestTemplate restT = new RestTemplate();
        String uri ="http://localhost:8085/product/account/findAccountByNumber?accountNumber=";
        Account fromAcc = restT.getForObject(uri+account,Account.class, Account.class);

        /*validando balance de cuenta*/
        validateBalance(fromAcc, amount);
        /*Seteando nuevos valores*/
        fromAcc.setActualBalance(fromAcc.getActualBalance().subtract(amount));
        fromAcc.setAvailableBalance(fromAcc.getAvailableBalance().subtract(amount));

        /*ANTIGUO - Actualizar Saldo en cuenta- FUNCIONA */
        String uri2 ="http://localhost:8085/product/account/updAccount";
        Account fromAc = restT.postForObject(uri2,fromAcc, Account.class);



        /*Generando transaccion*/
        Transaction trx = repositoryTra.save(Transaction.builder().transactionId(trxId).transactionType(TransactionType.UTILITY_PAYMENT).fromAccount(fromAcc.getNumber()).amount(amount.negate()).build());
        return trx;

    }

    private void validateBalance(Account account, BigDecimal amount){
        if (account.getActualBalance().compareTo(BigDecimal.ZERO) < 0 || account.getActualBalance().compareTo(amount) < 0) {
            throw new ValidationException("Saldo muy bajo para efectuar transaccion");
        }
    }


}

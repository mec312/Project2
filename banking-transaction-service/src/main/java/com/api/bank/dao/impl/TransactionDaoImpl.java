package com.api.bank.dao.impl;

import com.api.bank.dao.TransactionDao;
import com.api.bank.model.Transaction;
import com.api.bank.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TransactionDaoImpl implements TransactionDao {

    @Autowired
    private TransactionRepository repositoryTra;
    @Autowired
    private AccountRepository repositoryAcc;

    //tranferir fondos
    @Override
    public Transaction FundTransferTransaction(String fromAccount, String toAccount, BigDecimal amount){
        Account fromAcc = repositoryAcc.findAccountByNumber(fromAccount);
        Account toAcc = repositoryAcc.findAccountByNumber(toAccount);

        //validando balance de cuenta
        validateBalance(fromAcc, amount);
        String transactionId = internalFundTransfer(fromAcc, toAcc, amount);
        Transaction trx = Transaction.builder().transactionId(transactionId).amount(amount).build();
        return trx;
    }

    //pagar
    @Override
    public Transaction PayTransaction(BigDecimal amount,String account){
        String trxId = UUID.randomUUID().toString();//cambiar
        Account fromAccount = repositoryAcc.findAccountByNumber(account);

        //validando balance de cuenta
        validateBalance(fromAccount, amount);
        fromAccount.setActualBalance(fromAccount.getActualBalance().subtract(amount));
        fromAccount.setAvailableBalance(fromAccount.getActualBalance().subtract(amount));
        Transaction trx = repositoryTra.save(Transaction.builder().transactionType(TransactionType.UTILITY_PAYMENT).account(fromAccount).transactionId(trxId).referenceNumber(account).amount(amount.negate()).build());
        return trx;

    }

    private String internalFundTransfer(Account fromAccount, Account toAccount, BigDecimal amount){
        String trxId = UUID.randomUUID().toString();//cambiar
        Account fromAcc = repositoryAcc.findAccountByNumber(fromAccount.getNumber());
        Account toAcc = repositoryAcc.findAccountByNumber(toAccount.getNumber());

        fromAcc.setActualBalance(fromAcc.getActualBalance().subtract(amount));
        fromAcc.setAvailableBalance(fromAcc.getActualBalance().subtract(amount));
        repositoryAcc.save(fromAccount);
        repositoryTra.save(Transaction.builder().transactionType(TransactionType.FUND_TRANSFER).referenceNumber(toAcc.getNumber()).transactionId(trxId).account(fromAcc).amount(amount.negate()).build());

        toAcc.setActualBalance(toAcc.getActualBalance().add(amount));
        toAcc.setAvailableBalance(toAcc.getActualBalance().add(amount));
        repositoryAcc.save(toAcc);
        repositoryTra.save(Transaction.builder().transactionType(TransactionType.FUND_TRANSFER).referenceNumber(toAcc.getNumber()).transactionId(trxId).account(toAcc).amount(amount).build());

        return trxId;

    }

    private void validateBalance(Account account, BigDecimal amount){
        if (account.getActualBalance().compareTo(BigDecimal.ZERO) < 0 || account.getActualBalance().compareTo(amount) < 0) {
            throw new RuntimeException();
        }
    }


}

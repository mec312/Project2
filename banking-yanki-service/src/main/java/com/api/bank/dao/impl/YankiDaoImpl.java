package com.api.bank.dao.impl;

import com.api.bank.dao.YankiDao;
import com.api.bank.internal.*;
import com.api.bank.model.Account;
import com.api.bank.model.Transaction;
import com.api.bank.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
@Slf4j
public class YankiDaoImpl implements YankiDao {

    @Override
    public User createUserYanki(User uss){
        RestTemplate restT = new RestTemplate();
        String uri ="http://localhost:8084/user/regUser";
        User userYanki  = restT.postForObject(uri,uss, User.class);
        User uYanki = User.builder().dni(userYanki.getDni())
                .cellphoneImei(userYanki.getCellphoneImei())
                .userType(UserType.USER_YANKI)
                .userStatus(UserStatus.APPROVED)
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
                .transactionType(TransactionType.FUND_YANKI)
                .build();

        Transaction trxYanki  = restT.postForObject(uri, trx, Transaction.class);
        return trxYanki;
    }

    @Override
    public Transaction PayYanki(String fromAccount, BigDecimal amount){
        RestTemplate restT = new RestTemplate();

        String uri2 ="http://localhost:8086/transaction/payment";
        Transaction trx = Transaction.builder()
                .amount(amount)
                .fromAccount(fromAccount)
                .transactionType(TransactionType.PAYMENT_YANKI)
                .build();
        Transaction trxYanki  = restT.postForObject(uri2, trx, Transaction.class);
        return trxYanki;
    }

    @Override
    public Transaction MembershipYanki(String YankiAccount, String ChosenAccount){
        RestTemplate restT = new RestTemplate();
        String uri ="http://localhost:8085/product/account/findAccountByNumber?accountNumber=";
        log.info("uri+ChosenAccount:"+uri+ChosenAccount );
        Account chAcc = restT.getForObject(uri+ChosenAccount,Account.class, Account.class);

        Transaction trx = FundTransferYanki(ChosenAccount, YankiAccount, chAcc.getActualBalance());
        chAcc.setActualBalance(new BigDecimal(0));
        chAcc.setAvailableBalance(new BigDecimal(0));

        //Actualizar estado de la cuenta vaciada
        String uri2 ="http://localhost:8085/product/account/updAccount";
        chAcc.setStatus(AccountStatus.BLOCKED);
        Account accYanki  = restT.postForObject(uri2, chAcc, Account.class);

        return trx;
    }
}

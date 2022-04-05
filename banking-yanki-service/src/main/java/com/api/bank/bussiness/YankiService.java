package com.api.bank.bussiness;

import com.api.bank.model.Transaction;
import com.api.bank.model.User;
import io.reactivex.Maybe;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

public interface YankiService {
    Maybe<ResponseEntity<User>> createUserYanki(User uss);
    Maybe<ResponseEntity<Transaction>> FundTransferYanki(String fromAccount, String toAccount, BigDecimal amount);
    Maybe<ResponseEntity<Transaction>> PayYanki(String fromAccount, BigDecimal amount);
    Maybe<ResponseEntity<Transaction>> MembershipYanki(String YankiAccount, String ChosenAccount);
    Maybe<ResponseEntity<Transaction>> BuyYankiBootCoin(String fromAccount, BigDecimal amount);
}

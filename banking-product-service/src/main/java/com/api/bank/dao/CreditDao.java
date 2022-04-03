package com.api.bank.dao;

import com.api.bank.model.Account;
import com.api.bank.model.Credit;
import io.reactivex.Maybe;
import org.springframework.stereotype.Component;

@Component
public interface CreditDao {
    Credit findCreditByNumber(String creditNumber);

    Maybe<Credit> createCredit(Credit cre);
}

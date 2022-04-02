package com.api.bank.dao;

import com.api.bank.model.Account;
import com.api.bank.model.Credit;
import io.reactivex.Maybe;

public interface CreditDao {
    Maybe<Credit> findCreditByNumber(String creditNumber);

    Maybe<Credit> createCredit(Credit cre);
}

package com.api.bank.dao;

import com.api.bank.model.Account;
import com.api.bank.model.Credit;

public interface CreditDao {
    Credit findCreditByNumber(String creditNumber);

    Credit createCredit(Credit cre);
}

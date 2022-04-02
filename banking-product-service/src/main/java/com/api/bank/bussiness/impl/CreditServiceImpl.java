package com.api.bank.bussiness.impl;

import com.api.bank.bussiness.CreditService;
import com.api.bank.dao.CreditDao;
import com.api.bank.model.Credit;
import io.reactivex.Maybe;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class CreditServiceImpl implements CreditService {

    @Autowired
    private final CreditDao creditDao;

    @Override
    public Maybe<ResponseEntity<Credit>> findCreditByNumber(String creditNumber) {
        return creditDao.findCreditByNumber(creditNumber).map(cred ->validaRespuesta(cred));
    }

    public Maybe<ResponseEntity<Credit>> createCredit(Credit cre) {
        return creditDao.createCredit(cre).map(cred -> validaRespuesta(cred));
    }

    private ResponseEntity<Credit> validaRespuesta(Credit cre) {
        return (cre != null)
                ? ResponseEntity.ok(cre)
                : ResponseEntity.noContent().build();
    }

}

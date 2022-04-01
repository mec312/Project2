package com.api.bank.bussiness.impl;

import com.api.bank.bussiness.CreditService;
import com.api.bank.dao.CreditDao;
import com.api.bank.model.Credit;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class CreditServiceImpl implements CreditService {

    @Autowired
    private final CreditDao creditDao;

    @Override
    public ResponseEntity<Mono<Credit>> findCreditByNumber(String creditNumber) {
        Credit cred =  creditDao.findCreditByNumber(creditNumber);
        return validaRespuesta(cred);
    }

    public ResponseEntity<Mono<Credit>> createCredit(Credit cre) {
        Credit cred = creditDao.createCredit(cre);
        return validaRespuesta(cred);
    }

    private ResponseEntity<Mono<Credit>> validaRespuesta(Credit cred) {
        return (cred != null)
                ? ResponseEntity.ok(Mono.just(cred)) : ResponseEntity.noContent().build();
    }

    private ResponseEntity<Flux<Credit>> validaRespuesta(Flux<Credit> cred) {
        return (cred != null)
                ? ResponseEntity.ok(cred) : ResponseEntity.noContent().build();
    }
}

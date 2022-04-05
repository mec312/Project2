package com.api.bank.bussiness.impl;

import com.api.bank.bussiness.ExchangeService;
import com.api.bank.dao.ExchangeDao;
import com.api.bank.model.Exchange;
import com.ctc.wstx.util.StringUtil;
import io.reactivex.Maybe;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ExchangeServiceImpl implements ExchangeService {

    @Autowired
    private final ExchangeDao exchangeDao;

    @Override
    public Maybe<ResponseEntity<Exchange>> createExchange(Exchange request){
        return exchangeDao.createExchange(request).map(rq->validaRespuesta(rq));
    }

    @Override
    public Maybe<ResponseEntity<Exchange>> findExchange(String dc, String oc){
        return Maybe.just(exchangeDao.findExchange(dc.concat(oc)))
                .map(rq->validaRespuesta(rq));
    }

    private ResponseEntity<Exchange> validaRespuesta(Exchange ex) {
        return (ex != null)
                ? ResponseEntity.ok(ex)
                : ResponseEntity.noContent().build();
    }
}

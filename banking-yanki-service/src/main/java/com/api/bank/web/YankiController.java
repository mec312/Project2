package com.api.bank.web;

import com.api.bank.bussiness.YankiService;
import com.api.bank.model.User;
import io.reactivex.Maybe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/yanki")
public class YankiController {
    @Autowired
    private YankiService yankiService;

    @PostMapping(value = "/regUserYanki")
    public Maybe<ResponseEntity<User>> createUser(@Valid @RequestBody User request) {
        log.info("Creating user with {}", request.toString());
        return yankiService.createUserYanki(request);
    }

    @PostMapping(value = "/paymentYanki")
    public Maybe<ResponseEntity<Transaction>> PayTransaction(@Valid @RequestBody Transaction trx){
        log.info("Pagos - Inicio");
        return yankiService.PayTransaction(trx.getAmount(), trx.getFromAccount());
    }

    @PostMapping(value = "/fundTransferYanki")
    public Maybe<ResponseEntity<Transaction>> FundTransferTransaction(@Valid @RequestBody Transaction trx) {
        return yankiService.FundTransferTransaction(trx.getFromAccount(),
                trx.getToAccount(),
                trx.getAmount());
    }

    @PostMapping(value = "/membershipYanki")
    public Maybe<ResponseEntity<Transaction>> FundTransferTransaction(@Valid @RequestBody Transaction trx) {
        return yankiService.FundTransferTransaction(trx.getFromAccount(),
                trx.getToAccount(),
                trx.getAmount());
    }


}

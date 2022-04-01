package com.api.bank.web;

import com.api.bank.bussiness.AccountService;
import com.api.bank.model.Account;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Log4j2
@RestController
@RequestMapping(value = "/product")
public class ProductController {
    @Autowired
    private AccountService accountService;

    @GetMapping(value = "/account/findAccountByNumber")
    public ResponseEntity<Mono<Account>> findAccountByNumber(@RequestParam(required = true) String accountNumber) {
        return accountService.findAccountbyNumber(accountNumber);
    }

    @PostMapping(value = "/account/regAccount")
    public HttpStatus createAccount(@RequestBody Account request) {
        log.info("Creating user with {}", request.toString());
        ResponseEntity acc = accountService.findAccountbyNumber(request.getNumber());

        if (acc.getStatusCodeValue() != 200) {
            accountService.createAccount(request);
            log.info("ENTROOOOO 2 > " + request.toString());
            return HttpStatus.CREATED;
        } else {
            return HttpStatus.OK;
        }

    }
}

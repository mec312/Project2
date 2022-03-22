package com.api.bank.web;

import com.api.bank.bussiness.AccountService;
import com.api.bank.model.Account;
import com.api.bank.model.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping(value = "/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping(value = "/findAccountByNumber")
    public ResponseEntity findAccountByNumber(@RequestParam(required = true) String accountNumber) {
        return ResponseEntity.ok(accountService.findAccountbyNumber(accountNumber));
    }

    @PostMapping(value = "/regAccount")
    public HttpStatus createAccount(@RequestBody Account request) {
        log.info("Creating user with {}", request.toString());
        ResponseEntity acc = accountService.findAccountbyNumber(request.getNumber());

        if (acc.getStatusCodeValue() != 200) {
            accountService.createAccount(request);
            log.info("ENTROOOOO 2 > "+request.toString());
            return HttpStatus.CREATED;
        }else{
            return HttpStatus.OK;
        }

    }
}

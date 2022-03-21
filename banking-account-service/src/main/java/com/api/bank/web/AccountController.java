package com.api.bank.web;

import com.api.bank.bussiness.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping(value = "/findAccountByNumber")
    public ResponseEntity findAccountByNumber(@RequestParam(required = true) String accountNumber) {
        return ResponseEntity.ok(accountService.findAccountbyNumber(accountNumber));
    }

}

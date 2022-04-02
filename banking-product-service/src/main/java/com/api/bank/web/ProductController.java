package com.api.bank.web;

import com.api.bank.bussiness.AccountService;
import com.api.bank.bussiness.CreditService;
import com.api.bank.model.Account;
import com.api.bank.model.Credit;
import com.api.bank.model.User;
import io.reactivex.Maybe;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping(value = "/product")
public class ProductController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private CreditService creditService;

    @GetMapping(value = "/account/findAccountByNumber")
    public Maybe<ResponseEntity<Account>> findAccountByNumber(@RequestParam(required = true) String accountNumber) {
        return accountService.findAccountbyNumber(accountNumber);
    }

    @PostMapping(value = "/account/regAccount")
    public Maybe<ResponseEntity<Account>> createAccount(@Valid @RequestBody Account request) {
        log.info("Creating account with {}", request.toString());
        return accountService.createAccount(request);
    }

    @PostMapping(value = "/credit/regCredit")
    public Maybe<ResponseEntity<Credit>> createCredit(@Valid @RequestBody(required = true)Credit request){
        return creditService.createCredit(request);
    }

    @GetMapping(value = "/credit/findCreditByNumber")
    public Maybe<ResponseEntity<Credit>> findCreditbyNumber(@Valid @RequestParam(required = true) String creditNumber) {
        log.info("Reading user by id {}", creditService.findCreditByNumber(creditNumber));
        return creditService.findCreditByNumber(creditNumber);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


}

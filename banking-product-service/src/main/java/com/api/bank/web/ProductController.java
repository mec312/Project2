package com.api.bank.web;

import com.api.bank.bussiness.AccountService;
import com.api.bank.bussiness.CreditService;
import com.api.bank.model.Account;
import com.api.bank.model.Credit;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import io.reactivex.Maybe;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;

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

    @CircuitBreaker(name = "findAccountByNumber",fallbackMethod ="subscribesFallbackMethod")
    @RateLimiter(name = "findAccountByNumber")
    @Bulkhead(name = "findAccountByNumber")
    @Retry(name = "findAccountByNumber", fallbackMethod = "subscribesFallbackMethod")
    @TimeLimiter(name = "findAccountByNumber")
    @GetMapping(value = "/account/findAccountByNumber")
    public Maybe<ResponseEntity<Account>> findAccountByNumber(@RequestParam(required = true) String accountNumber) {
        return accountService.findAccountbyNumber(accountNumber);
    }

    @CircuitBreaker(name = "createAccount",fallbackMethod ="subscribesFallbackMethod")
    @RateLimiter(name = "createAccount")
    @Bulkhead(name = "createAccount")
    @Retry(name = "createAccount", fallbackMethod = "subscribesFallbackMethod")
    @TimeLimiter(name = "createAccount")
    @PostMapping(value = "/account/regAccount")
    public Maybe<ResponseEntity<Account>> createAccount(@Valid @RequestBody Account request) {
        log.info("Creating account with {}", request.toString());
        return accountService.createAccount(request);
    }

    @CircuitBreaker(name = "updateAccount",fallbackMethod ="subscribesFallbackMethod")
    @RateLimiter(name = "updateAccount")
    @Bulkhead(name = "updateAccount")
    @Retry(name = "updateAccount", fallbackMethod = "subscribesFallbackMethod")
    @TimeLimiter(name = "updateAccount")
    @PostMapping(value = "/account/updAccount")
    public Maybe<ResponseEntity<Account>> updateAccount(@Valid @RequestBody(required = true)Account request){
        return accountService.updateAccount(request);
    }

    @CircuitBreaker(name = "createCredit",fallbackMethod ="subscribesFallbackMethod")
    @RateLimiter(name = "createCredit")
    @Bulkhead(name = "createCredit")
    @Retry(name = "createCredit", fallbackMethod = "subscribesFallbackMethod")
    @TimeLimiter(name = "createCredit")
    @PostMapping(value = "/credit/regCredit")
    public Maybe<ResponseEntity<Credit>> createCredit(@Valid @RequestBody(required = true)Credit request){
        return creditService.createCredit(request);
    }
    @CircuitBreaker(name = "findCreditbyNumber",fallbackMethod ="subscribesFallbackMethod")
    @RateLimiter(name = "findCreditbyNumber")
    @Bulkhead(name = "findCreditbyNumber")
    @Retry(name = "findCreditbyNumber", fallbackMethod = "subscribesFallbackMethod")
    @TimeLimiter(name = "findCreditbyNumber")
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

    //resilience4j
    public ResponseEntity<String> subscribesFallbackMethod(Exception e) {
        return new ResponseEntity<String>("Servicio esta caido, intente mas tarde", HttpStatus.OK);
    }
}

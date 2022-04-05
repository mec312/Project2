package com.api.bank.web;

import com.api.bank.bussiness.TransactionService;
import com.api.bank.model.Transaction;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import io.reactivex.Maybe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;


    @CircuitBreaker(name = "fundTransfer",fallbackMethod ="subscribesFallbackMethod")
    @RateLimiter(name = "fundTransfer")
    @Bulkhead(name = "fundTransfer")
    @Retry(name = "fundTransfer", fallbackMethod = "subscribesFallbackMethod")
    @TimeLimiter(name = "fundTransfer")
    @PostMapping(value = "/fundTransfer")
    public Maybe<ResponseEntity<Transaction>> FundTransferTransaction(@Valid @RequestBody Transaction trx) {
        return transactionService.FundTransferTransaction(trx.getFromAccount(),
                trx.getToAccount(),
                trx.getAmount());
    }

    @CircuitBreaker(name = "payment",fallbackMethod ="subscribesFallbackMethod")
    @RateLimiter(name = "payment")
    @Bulkhead(name = "payment")
    @Retry(name = "payment", fallbackMethod = "subscribesFallbackMethod")
    @TimeLimiter(name = "payment")
    @PostMapping(value = "/payment")
    public Maybe<ResponseEntity<Transaction>> PayTransaction(@Valid @RequestBody Transaction trx){
        log.info("Pagos - Inicio");
        return transactionService.PayTransaction(trx.getAmount(), trx.getFromAccount());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
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

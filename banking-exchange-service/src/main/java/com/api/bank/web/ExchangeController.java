package com.api.bank.web;

import com.api.bank.bussiness.ExchangeService;
import com.api.bank.model.Exchange;
import io.reactivex.Maybe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/exchange")
public class ExchangeController {
    @Autowired
    private ExchangeService exchangeService;

    @PostMapping(value = "/createExchange")
    public Maybe<ResponseEntity<Exchange>> createExchange(@Valid @RequestBody Exchange request) {
        request.setCode(request.getOriginCurrency().concat(request.getDestinationCurrency()));
        log.info("Creating account with {}", request.toString());
        return exchangeService.createExchange(request);
    }

    @GetMapping(value = "/findExchange")
    public Maybe<ResponseEntity<Exchange>> findExchange(@RequestParam(required = true) String originCurrency, String destinationCurrency) {
        return exchangeService.findExchange(originCurrency, destinationCurrency);
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

}

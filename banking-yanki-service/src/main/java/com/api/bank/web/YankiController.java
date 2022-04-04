package com.api.bank.web;

import com.api.bank.bussiness.YankiService;
import com.api.bank.model.Transaction;
import com.api.bank.model.User;
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
@RequestMapping(value = "/yanki")
public class YankiController {
    @Autowired
    private YankiService yankiService;

    @PostMapping(value = "/regUserYanki")
    public Maybe<ResponseEntity<User>> regUserYanki(@Valid @RequestBody User request) {
        log.info("Creating user with {}", request.toString());
        return yankiService.createUserYanki(request);
    }

    @PostMapping(value = "/paymentYanki")
    public Maybe<ResponseEntity<Transaction>> paymentYanki(@Valid @RequestBody Transaction trx){
        log.info("Pagos - Inicio");
        return yankiService.PayYanki(trx.getFromAccount(), trx.getAmount());
    }

    @PostMapping(value = "/fundTransferYanki")
    public Maybe<ResponseEntity<Transaction>> fundTransferYanki(@Valid @RequestBody Transaction trx) {
        return yankiService.FundTransferYanki(trx.getFromAccount(),
                trx.getToAccount(),
                trx.getAmount());
    }

    @PostMapping(value = "/membershipYanki")
    @ResponseBody
    public Maybe<ResponseEntity<Transaction>> membershipYanki(@RequestParam(required = false, name="yankiaccount") String yankiaccount,  @RequestParam(required = false, name="chosenaccount") String chosenaccount) {
        return yankiService.MembershipYanki(yankiaccount, chosenaccount);
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

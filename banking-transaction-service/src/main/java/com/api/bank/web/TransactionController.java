package com.api.bank.web;

import com.api.bank.bussiness.TransactionService;
import com.api.bank.model.Transaction;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Log4j2
@RestController(value = "/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping(value = "/fund-transfer")
    public ResponseEntity FundTransferTransaction(@Valid @RequestBody Transaction trx) {
        log.info("Transferecnia de Fondos- Inicio:", trx.toString());
        return transactionService.FundTransferTransaction(trx.getAccount().getNumber(), trx.getReferenceNumber(),trx.getAmount());
    }

    @PostMapping(value = "/payment")
    public ResponseEntity PayTransaction(@Valid @RequestBody Transaction trx){
        log.info("Pagos - Inicio");
        return transactionService.PayTransaction(trx.getAmount(), trx.getAccount().getNumber());
    }

}

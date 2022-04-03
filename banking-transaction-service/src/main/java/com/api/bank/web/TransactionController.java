package com.api.bank.web;

import com.api.bank.bussiness.TransactionService;
import com.api.bank.model.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping(value = "/fundTransfer")
    public String FundTransferTransaction(
            //@Valid @RequestBody Transaction trx
            ) {
        return "HOLA MEC";
        //return transactionService.FundTransferTransaction(trx.getFromAccount().getNumber(), trx.getToAccount().getNumber(),trx.getAmount());
    }

    @PostMapping(value = "/payment")
    public Mono<ResponseEntity<Transaction>> PayTransaction(@Valid @RequestBody Transaction trx){
        log.info("Pagos - Inicio");
        return transactionService.PayTransaction(trx.getAmount(), trx.getFromAccount().getNumber());
    }

}

package com.api.bank.model;

import com.api.bank.internal.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "Transaction")
public class Transaction {
    @Id
    @Field("_id")
    @JsonIgnore
    private String id;

    private BigDecimal amount;

    //@Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private String referenceNumber;

    private String transactionId;

    //private Account account;

}

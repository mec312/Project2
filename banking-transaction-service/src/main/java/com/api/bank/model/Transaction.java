package com.api.bank.model;

import com.api.bank.internal.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.lang.Nullable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(value = "Transaction")
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String id;

    @NotNull
    @Valid
    private String transactionId;

    @NotNull
    @Valid
    private BigDecimal amount;

    @NotNull
    @Valid
    private TransactionType transactionType;

    @NotNull
    @Valid
    private Account fromAccount;

    @Nullable
    @Valid
    private Account toAccount;

}

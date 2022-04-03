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

package com.api.bank.model;

import com.api.bank.internal.AccountStatus;
import com.api.bank.internal.AccountType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "Account")
public class Account {

    @Id
    @Field("_id")
    @JsonIgnore
    private String id;

    private String number;

    private AccountType type;

    private AccountStatus status;

    private BigDecimal availableBalance;

    private BigDecimal actualBalance;

    private User user;
}

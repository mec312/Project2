package com.api.bank.model;

import com.api.bank.internal.AccountStatus;
import com.api.bank.internal.AccountType;
import com.api.bank.internal.CreditStatus;
import com.api.bank.internal.CreditType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "Credit")
public class Credit {
    @Id
    @Field("_id")
    @JsonIgnore
    private String id;

    @NotNull
    @Valid
    private String number;

    @NotNull
    @Valid
    private CreditType type;

    @NotNull
    @Valid
    private CreditStatus status;

    @NotNull
    @Valid
    private BigDecimal availableBalance;

    @NotNull
    @Valid
    private BigDecimal actualBalance;

    @NotNull
    @Valid
    private User user;
}

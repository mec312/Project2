package com.api.bank.model;

import com.api.bank.internal.AccountStatus;
import com.api.bank.internal.AccountType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "Account")
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Valid
    private String number;

    @NotNull
    @Valid
    private AccountType type;

    @NotNull
    @Valid
    private AccountStatus status;

    @NotNull
    @Valid
    private BigDecimal availableBalance;

    @NotNull
    @Valid
    private BigDecimal actualBalance;

    @NotNull
    @Valid
    private String userDni;
}

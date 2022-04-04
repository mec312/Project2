package com.api.bank.model;

import com.api.bank.internal.AccountStatus;
import com.api.bank.internal.AccountType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

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

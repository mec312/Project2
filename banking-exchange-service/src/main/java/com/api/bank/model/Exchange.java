package com.api.bank.model;

import com.api.bank.internal.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class Exchange {
    @Valid
    @NotNull
    private BigDecimal amount;

    @Valid
    @NotNull
    private BaseModel originCurrency;

    @Valid
    @NotNull
    private BaseModel destinationCurrency;

    @Valid
    @NotNull
    private Boolean isActive;
}

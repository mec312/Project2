package com.api.bank.request;

import com.api.bank.internal.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class ExchangeRequest {
    @Valid
    @NotNull
    private BaseModel originCurrency;

    @Valid
    @NotNull
    private BaseModel destinationCurrency;

}

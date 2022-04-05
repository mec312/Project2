package com.api.bank.request;

import com.api.bank.internal.BaseModel;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRequest {
    @Valid
    @NotNull
    private BaseModel originCurrency;

    @Valid
    @NotNull
    private BaseModel destinationCurrency;

}

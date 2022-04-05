package com.api.bank.model;

import com.api.bank.internal.BaseModel;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(value = "Exchange")
public class Exchange {
    private static final long serialVersionUID = 1L;
    @Id
    private String id;

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

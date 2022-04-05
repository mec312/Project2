package com.api.bank.model;

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
@ToString
@Document(value = "Exchange")
public class Exchange  implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String id;


    private String code;

    private BigDecimal amount;


    private String originCurrency;


    private String destinationCurrency;


    private Boolean isActive;
}

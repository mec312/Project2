package com.api.bank.model;

import com.api.bank.internal.CreditStatus;
import com.api.bank.internal.CreditType;
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
@Document(value = "Credit")
public class Credit implements Serializable {

    private static final long serialVersionUID = 1L;

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
    private BigDecimal amount;

    @NotNull
    @Valid
    private Integer fees;

    @NotNull
    @Valid
    private User user;
}

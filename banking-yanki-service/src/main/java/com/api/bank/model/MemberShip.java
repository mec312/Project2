package com.api.bank.model;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberShip {
    @NotNull
    @Valid
    private String yankiaccount;

    @NotNull
    @Valid
    private String chosenaccount;

}

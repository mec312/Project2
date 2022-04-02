package com.api.bank.model;

import com.api.bank.internal.UserStatus;
import com.api.bank.internal.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@Document(value = "User")
public class User {

    @Id
    @Field("_id")
    @JsonIgnore
    private String id;
    @Valid
    @NotNull
    private String firstName;
    @Valid
    @NotNull
    private String lastName;
    @Valid
    @NotNull
    private int age;
    @Valid
    @NotNull
    private String dni;
    @Valid
    @NotNull
    private UserStatus status;
    @Valid
    @NotNull
    private UserType type;
}

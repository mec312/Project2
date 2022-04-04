package com.api.bank.model;

import com.api.bank.internal.UserStatus;
import com.api.bank.internal.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.lang.Nullable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Field("_id")
    @JsonIgnore
    private String id;
    @Valid
    @Nullable
    private String firstName;
    @Valid
    @Nullable
    private String lastName;
    @Valid
    @Nullable
    private int age;

    @Valid
    @NotNull
    private String dni;
    @Valid
    @NotNull
    private String cellphoneNumber;
    @Valid
    @NotNull
    private String cellphoneImei;
    @Valid
    @NotNull
    private String email;

    @Valid
    @Nullable
    private UserStatus userStatus;
    @Valid
    @Nullable
    private UserType userType;
}

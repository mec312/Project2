package com.api.bank.model;

import com.api.bank.internal.UserStatus;
import com.api.bank.internal.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

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
    private String firstName;
    private String lastName;
    private int age;
    private String dni;
    private UserStatus status;
    private UserType type;
}
package com.api.bank.model;

import com.api.bank.internal.UserStatus;
import com.api.bank.internal.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.lang.Nullable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(value = "User")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String id;

    private String firstName;

    private String lastName;

    private int age;


    private String dni;

    private String cellphoneNumber;

    private String cellphoneImei;

    private String email;


    private UserStatus userStatus;

    private UserType userType;

}

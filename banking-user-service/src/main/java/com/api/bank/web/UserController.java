package com.api.bank.web;

import com.api.bank.bussiness.UserService;
import com.api.bank.model.User;
import io.reactivex.Maybe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;


import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/regUser")
    public Maybe<ResponseEntity<User>> createUser(@Valid @RequestBody User request) {
        log.info("Creating user with {}", request.toString());
        return userService.createUser(request);
    }

    @PostMapping(value = "/updUser")
    public Maybe<ResponseEntity<User>> updateUser(@RequestBody(required = true) String dni, @RequestBody(required = true) User uss) {
        log.info("Updating user", uss.toString());
        return userService.updateUser(dni, uss.getUserStatus());
    }

    @GetMapping(value = "/findUserbyDni")
    public Maybe<ResponseEntity<User>> findByDni(@RequestParam(required = true) String dni) {
        log.info("Reading user by id {}", userService.findByDni(dni));
        return userService.findByDni(dni);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}

package com.api.bank.web;

import com.api.bank.bussiness.UserService;
import com.api.bank.model.User;
import io.reactivex.Maybe;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Log4j2
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
    public ResponseEntity updateUser(@RequestBody(required = true) String dni, @RequestBody(required = true) User uss) {
        log.info("Updating user", uss.toString());
        return ResponseEntity.ok(userService.updateUser(dni, uss.getUserStatus()));
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

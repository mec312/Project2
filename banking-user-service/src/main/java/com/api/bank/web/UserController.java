package com.api.bank.web;

import com.api.bank.bussiness.UserService;
import com.api.bank.model.User;
import io.reactivex.Maybe;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Log4j2
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/regUser")
    public Maybe<ResponseEntity<User>> createUser(@RequestBody User request) {
        log.info("Creating user with {}", request.toString());
        return userService.createUser(request);

    }

    @PostMapping(value = "/updUser")
    public ResponseEntity updateUser(@RequestBody(required = true) String dni, @RequestBody(required = true)User uss) {
        log.info("Updating user", uss.toString());
        return ResponseEntity.ok(userService.updateUser(dni, uss.getUserStatus()));
    }

    @GetMapping(value = "/findUserbyDni")
    public Maybe<ResponseEntity<User>> findByDni(@RequestParam(required = true) String dni) {
        log.info("Reading user by id {}", userService.findByDni(dni));
        return userService.findByDni(dni);
    }
}

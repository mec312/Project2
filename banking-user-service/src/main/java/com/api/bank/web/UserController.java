package com.api.bank.web;

import com.api.bank.bussiness.UserService;
import com.api.bank.model.User;
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
    public HttpStatus createUser(@RequestBody User request) {
        log.info("Creating user with {}", request.toString());
        ResponseEntity us = userService.findByDni(request.getDni());

        if (us.getStatusCodeValue() != 200) {
            userService.createUser(request);
            log.info("ENTROOOOO 2 > "+request.toString());
            return HttpStatus.CREATED;
        }else{
            return HttpStatus.OK;
        }

    }

    @PostMapping(value = "/updUser")
    public ResponseEntity updateUser(@RequestBody(required = true) String dni, @RequestBody(required = true)User uss) {
        log.info("Updating user", uss.toString());
        return ResponseEntity.ok(userService.updateUser(dni, uss.getUserStatus()));
    }

    @GetMapping(value = "/findUserbyDni")
    public ResponseEntity<Mono<User>> findByDni(@RequestParam(required = true) String dni) {
        log.info("Reading user by id {}", userService.findByDni(dni));
        return userService.findByDni(dni);
    }
}

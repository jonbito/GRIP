package com.bishopsoft.grip.api.controller;

import com.bishopsoft.grip.api.dto.AccountSignupBindingModel;
import com.bishopsoft.grip.api.service.KeycloakService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final KeycloakService keycloakService;

    public AccountController(KeycloakService keycloakService) {
        this.keycloakService = keycloakService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody @Valid AccountSignupBindingModel accountSignupBindingModel) {
        keycloakService.createUser(accountSignupBindingModel.getEmail(), accountSignupBindingModel.getPassword(),
                accountSignupBindingModel.getFirstName(), accountSignupBindingModel.getLastName());
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @GetMapping("/userExists/{email}")
    public boolean userExists(@PathVariable("email") String email) {
        return keycloakService.userExists(email);
    }
}

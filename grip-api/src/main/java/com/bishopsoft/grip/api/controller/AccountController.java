package com.bishopsoft.grip.api.controller;

import com.bishopsoft.grip.api.dto.AccountSignupBindingModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
public class AccountController {

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody @Valid AccountSignupBindingModel accountSignupBindingModel) {
        return new ResponseEntity(HttpStatus.OK);
    }
}

package com.bishopsoft.grip.api.account;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<String> signUp(@RequestBody @Valid AccountSignupBindingModel accountSignupBindingModel, Principal principal) {
        accountService.createAccount(accountSignupBindingModel.getRole(), accountSignupBindingModel.getWhosUsing(), principal);
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}

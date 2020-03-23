package com.bishopsoft.grip.api.account;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
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
        accountService.createAccount(accountSignupBindingModel.getRole(),
                accountSignupBindingModel.getWhosUsing(),
                accountSignupBindingModel.getUsername(),
                principal);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @GetMapping(value = "/avatar", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getAvatar() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("user-avatar/default-avatar.png", getClass().getClassLoader());
        return classPathResource.getInputStream().readAllBytes();
    }
}

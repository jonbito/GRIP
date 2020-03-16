package com.bishopsoft.grip.api.controller;

import com.bishopsoft.grip.api.dto.AccountSignupBindingModel;
import com.bishopsoft.grip.api.dto.RoleDTO;
import com.bishopsoft.grip.api.model.User;
import com.bishopsoft.grip.api.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody @Valid AccountSignupBindingModel accountSignupBindingModel, Principal principal) {
        accountService.createAccount(accountSignupBindingModel.getRole(), accountSignupBindingModel.getWhosUsing(), principal);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @GetMapping("/roles")
    public List<RoleDTO> getRoles() {
        return Stream.of(User.Role.values())
                .map(r -> RoleDTO.builder().text(r.getNiceText()).value(r.name()).build())
                .collect(Collectors.toList());
    }
}

package com.bishopsoft.grip.api.service;

import com.bishopsoft.grip.api.exception.HttpException;
import com.bishopsoft.grip.api.model.Account;
import com.bishopsoft.grip.api.model.User;
import com.bishopsoft.grip.api.repository.AccountRepository;
import com.bishopsoft.grip.api.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.UUID;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final KeycloakService keycloakService;

    public AccountService(AccountRepository accountRepository, UserRepository userRepository, KeycloakService keycloakService) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.keycloakService = keycloakService;
    }

    @Transactional
    public void createAccount(String role, String whosUsing, Principal principal) {
        boolean userExists = userRepository.findById(UUID.fromString(principal.getName())).isPresent();
        if(userExists) {
            throw new HttpException("User already exists", HttpStatus.BAD_REQUEST);
        }

        Account account = new Account();

        User user = new User();
        user.setId(UUID.fromString(principal.getName()));
        user.setAccount(account);
        user.setRole(role);

        userRepository.save(user);

        keycloakService.setUserAttribute(UUID.fromString(principal.getName()), "accountCreated", "true");
    }
}

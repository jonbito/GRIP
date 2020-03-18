package com.bishopsoft.grip.api.account;

import com.bishopsoft.grip.api.infrastructure.exception.HttpException;
import com.bishopsoft.grip.api.infrastructure.model.UserAccount;
import com.bishopsoft.grip.api.infrastructure.repository.UserRepository;
import com.bishopsoft.grip.api.infrastructure.service.KeycloakService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.UUID;

@Service
public class AccountService {
    private final UserRepository userRepository;
    private final KeycloakService keycloakService;

    public AccountService(UserRepository userRepository, KeycloakService keycloakService) {
        this.userRepository = userRepository;
        this.keycloakService = keycloakService;
    }

    @Transactional
    public void createAccount(String role, String whosUsing, Principal principal) {
        boolean userExists = userRepository.findById(UUID.fromString(principal.getName())).isPresent();
        if(userExists) {
            throw new HttpException("User already exists", HttpStatus.BAD_REQUEST);
        }

        UserAccount userAccount = new UserAccount();
        userAccount.setId(UUID.fromString(principal.getName()));
        userAccount.setRole(role);

        userRepository.save(userAccount);

        keycloakService.setUserAttribute(UUID.fromString(principal.getName()), "accountCreated", "true");
    }
}

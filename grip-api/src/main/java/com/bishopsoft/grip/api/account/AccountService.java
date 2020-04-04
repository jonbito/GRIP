package com.bishopsoft.grip.api.account;

import com.bishopsoft.grip.api.infrastructure.exception.HttpException;
import com.bishopsoft.grip.api.infrastructure.model.UserAccount;
import com.bishopsoft.grip.api.infrastructure.repository.UserRepository;
import com.bishopsoft.grip.api.infrastructure.service.KeycloakService;
import javafx.util.Pair;
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
    public void createAccount(String role, String whosUsing, String username, Principal principal) {
        boolean userExists = userRepository.findById(UUID.fromString(principal.getName())).isPresent();
        if(userExists) {
            throw new HttpException("User already exists", HttpStatus.BAD_REQUEST);
        }

        if(usernameExists(username)) {
            throw new HttpException("Username already exists", HttpStatus.BAD_REQUEST);
        }

        UserAccount userAccount = new UserAccount();
        userAccount.setId(UUID.fromString(principal.getName()));
        userAccount.setUsername(username);
        userAccount.setRole(role);

        Pair<String, String> fullName = keycloakService.getFullName(UUID.fromString(principal.getName()));
        userAccount.setFirstName(fullName.getKey());
        userAccount.setLastName(fullName.getValue());

        userRepository.save(userAccount);

        keycloakService.setUserAttribute(UUID.fromString(principal.getName()), "accountCreated", "true");
        keycloakService.setUsername(UUID.fromString(principal.getName()), username);
    }

    public boolean usernameExists(String username) {
        return userRepository.existsUserAccountByUsername(username);
    }
}

package com.bishopsoft.grip.api.infrastructure.service;

import com.bishopsoft.grip.api.infrastructure.exception.HttpException;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class KeycloakService {
    private final Keycloak keycloak;

    @Value("${keycloak.realm}")
    private String realm;

    public KeycloakService(Keycloak keycloak) {
        this.keycloak = keycloak;
    }

    public UUID createUser(final String email, final String password, final String firstName, final String lastName) {
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue(password);

        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(email);
        userRepresentation.setFirstName(firstName);
        userRepresentation.setLastName(lastName);
        userRepresentation.setEmail(email);
        userRepresentation.setCredentials(Collections.singletonList(credentialRepresentation));
        Response response = keycloak.realm(realm).users().create(userRepresentation);

        if(response.getStatus() == HttpStatus.CONFLICT.value()) {
            throw new HttpException("User already exists", HttpStatus.BAD_REQUEST);
        }

        if(response.getStatus() != HttpStatus.CREATED.value() || response.getLocation() == null) {
            throw new HttpException("Unable to create user in keycloak", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        String path = response.getLocation().getPath();
        String userId = path.substring(path.lastIndexOf("/") + 1);

        CredentialRepresentation newCredential = new CredentialRepresentation();
        UserResource userResource = keycloak.realm(realm).users().get(userId);
        newCredential.setType(CredentialRepresentation.PASSWORD);
        newCredential.setValue(password);
        newCredential.setTemporary(false);
        userResource.resetPassword(newCredential);

        return UUID.fromString(userId);
    }

    public void deleteUser(UUID userId) {
        Response response = keycloak.realm(realm).users().delete(userId.toString());

        if(response.getStatus() != HttpStatus.OK.value()) {
            throw new HttpException("Unable to delete user in keycloak", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public boolean userExists(String email) {
        List<UserRepresentation> users = keycloak.realm(realm).users().search(email);
        return !users.isEmpty();
    }

    public void setUserAttribute(UUID userId, String attribute, String value) {
        UserResource userResource = keycloak.realm(realm).users().get(userId.toString());
        UserRepresentation userRepresentation = userResource.toRepresentation();
        userRepresentation.singleAttribute(attribute, value);
        userResource.update(userRepresentation);
    }

    public UserRepresentation getUserRepresentation(UUID userId) {
        UserResource userResource = keycloak.realm(realm).users().get(userId.toString());
        return userResource.toRepresentation();
    }
}

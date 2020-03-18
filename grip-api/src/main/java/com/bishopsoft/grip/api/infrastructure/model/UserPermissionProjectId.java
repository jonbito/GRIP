package com.bishopsoft.grip.api.infrastructure.model;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class UserPermissionProjectId implements Serializable {
    private UUID user;
    private long project;
}

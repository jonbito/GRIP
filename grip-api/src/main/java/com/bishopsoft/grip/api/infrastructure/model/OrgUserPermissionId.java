package com.bishopsoft.grip.api.infrastructure.model;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class OrgUserPermissionId implements Serializable {
    private UUID user;
    private long org;
}

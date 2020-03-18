package com.bishopsoft.grip.api.infrastructure.security;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class LoggedInUser {
    private UUID id;
}

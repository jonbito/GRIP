package com.bishopsoft.grip.api.account;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleDTO {
    private String text;
    private String value;
}

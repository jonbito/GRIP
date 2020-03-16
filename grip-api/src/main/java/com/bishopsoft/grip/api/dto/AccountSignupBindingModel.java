package com.bishopsoft.grip.api.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AccountSignupBindingModel {
    @NotNull
    private String role;
    @NotBlank
    private String whosUsing;
}

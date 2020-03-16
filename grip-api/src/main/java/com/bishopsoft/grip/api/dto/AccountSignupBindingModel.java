package com.bishopsoft.grip.api.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AccountSignupBindingModel {
    @NotBlank
    private String role;
    @NotBlank
    private String whosUsing;
}

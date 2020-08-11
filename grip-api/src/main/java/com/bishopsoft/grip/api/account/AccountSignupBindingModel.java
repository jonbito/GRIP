package com.bishopsoft.grip.api.account;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class AccountSignupBindingModel {
    @NotBlank
    private String role;
    @NotBlank
    private String whosUsing;
}

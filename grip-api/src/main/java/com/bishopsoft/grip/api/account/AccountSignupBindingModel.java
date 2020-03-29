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
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Please create a username with only alphanumeric characters")
    @Length(max = 127, message = "Username too long. (maximum is 127 characters)")
    private String username;
    @NotBlank
    private String whosUsing;
}

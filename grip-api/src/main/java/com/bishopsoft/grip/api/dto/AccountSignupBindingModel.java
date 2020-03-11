package com.bishopsoft.grip.api.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class AccountSignupBindingModel {
    @NotBlank(message = "First name must not be blank")
    @Pattern(regexp = "^\\p{Alpha}{1,64}$", message = "First name must be 1 to 64 alphabetic characters")
    private String firstName;

    @NotBlank(message = "Last name must not be blank")
    @Pattern(regexp = "^\\p{Alpha}{1,64}$", message = "Last name must be 1 to 64 alphabetic characters")
    private String lastName;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Email must be a valid email address")
    private String email;

    @NotBlank(message = "Password must not be blank")
    @Length(min = 6, max = 32, message = "Password must be between 6 and 32 characters")
    private String password;
}

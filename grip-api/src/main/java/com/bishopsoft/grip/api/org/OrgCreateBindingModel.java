package com.bishopsoft.grip.api.org;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class OrgCreateBindingModel {
    @NotBlank
    @Pattern(
            regexp = "^[0-9A-Z-\\s]*$",
            flags = { Pattern.Flag.CASE_INSENSITIVE },
            message = "Name may only contain alphanumeric characters or single hyphens"
            )
    private String name;

    @NotBlank
    @Email
    private String email;
}

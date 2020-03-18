package com.bishopsoft.grip.api.project;

import com.bishopsoft.grip.api.infrastructure.validator.StartsWithLetterConstraint;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class ProjectCreateBindingModel {

    @NotBlank
    @Length(max = 50, message = "Project name must be less than 50 characters")
    private String name;

    @NotBlank
    @Length(max = 10, message = "Project key must be less than 10 characters")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Project keys must start with an uppercase letter, followed by one or more uppercase alphanumeric characters")
    @StartsWithLetterConstraint(message = "Project keys must start with an uppercase letter, followed by one or more uppercase alphanumeric characters")
    private String key;
}

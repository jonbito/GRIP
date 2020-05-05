package com.bishopsoft.grip.api.operation;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class OperationCreateBindingModel {

    @NotNull(message = "Project Id is required")
    private Long projectId;

    @NotBlank
    @Length(max = 50, message = "Operation name must be less than 50 characters")
    private String name;

    @Length(max = 1000, message = "Operation description must be less that 1000 characters")
    private String description;
}
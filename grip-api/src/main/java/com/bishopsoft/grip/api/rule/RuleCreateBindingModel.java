package com.bishopsoft.grip.api.rule;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RuleCreateBindingModel {
    @NotNull(message = "Goal Id is required")
    private Long goalId;
    @NotBlank(message = "Name is required")
    private String name;
    private String description;
}

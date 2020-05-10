package com.bishopsoft.grip.api.goal;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class GoalCreateBindingModel {
    private Long issueId;
    private Long goalId;
    @NotBlank(message = "Summary is required")
    private String summary;
    @Length(max = 5000, message = "Description must be less than 5000 characters")
    private String description;
    private Integer size;
}

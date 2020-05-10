package com.bishopsoft.grip.api.issue;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class IssuePatchBindingModel {
    @NotNull(message = "Project Id is required")
    private Long projectId;
    @NotBlank(message = "Summary is required")
    private String summary;
    private String description;
}

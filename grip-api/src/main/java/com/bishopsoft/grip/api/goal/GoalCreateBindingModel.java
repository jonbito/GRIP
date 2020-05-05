package com.bishopsoft.grip.api.goal;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GoalCreateBindingModel {
    @NotNull(message = "Agent Id is required")
    private Long projectId;
    private Long goalId;
    @NotNull(message = "Agent Id is required")
    private Long agentId;
    @NotNull(message = "Subject Id is required")
    private Long subjectId;
    @NotNull(message = "Operation Id is required")
    private Long operationId;

    private Integer size;
}

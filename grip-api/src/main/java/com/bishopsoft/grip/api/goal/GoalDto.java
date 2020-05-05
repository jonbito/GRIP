package com.bishopsoft.grip.api.goal;

import com.bishopsoft.grip.api.agent.AgentDto;
import com.bishopsoft.grip.api.operation.OperationDto;
import com.bishopsoft.grip.api.subject.SubjectDto;
import lombok.Data;

import java.util.List;

@Data
public class GoalDto {
    private long id;
    private long niceId;
    private Integer size;
    private AgentDto agent;
    private OperationDto operation;
    private SubjectDto subject;
    private GoalDto parent;
    private List<GoalDto> children;
}

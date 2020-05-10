package com.bishopsoft.grip.api.goal;

import lombok.Data;

import java.util.List;

@Data
public class GoalDto {
    private long id;
    private long niceId;
    private Integer size;
    private String summary;
    private String description;
    private List<GoalDto> children;
}

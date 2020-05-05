package com.bishopsoft.grip.api.rule;

import lombok.Data;

import java.util.Date;

@Data
public class RuleDto {
    private long id;
    private long niceId;
    private String name;
    private String description;
    private Date completeDate;
    private long goalId;
}

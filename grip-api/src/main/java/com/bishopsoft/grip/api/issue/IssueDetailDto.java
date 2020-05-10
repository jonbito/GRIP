package com.bishopsoft.grip.api.issue;

import lombok.Data;

@Data
public class IssueDetailDto {
    private long id;
    private long niceId;
    private String summary;
    private String description;
}

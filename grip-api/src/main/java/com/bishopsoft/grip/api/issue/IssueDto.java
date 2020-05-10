package com.bishopsoft.grip.api.issue;

import lombok.Data;

@Data
public class IssueDto {
    private long id;
    private long niceId;
    private String summary;
    private String description;
    private long projectId;
}

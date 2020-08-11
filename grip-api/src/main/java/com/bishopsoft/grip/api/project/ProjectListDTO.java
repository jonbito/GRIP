package com.bishopsoft.grip.api.project;

import lombok.Data;

@Data
public class ProjectListDTO {
    private long id;
    private String name;
    private String key;
    private boolean starred;
    private String leadId;
    private String leadName;
    private String leadAvatar;
}

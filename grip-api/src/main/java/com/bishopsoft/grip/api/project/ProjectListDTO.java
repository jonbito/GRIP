package com.bishopsoft.grip.api.project;

import lombok.Data;

@Data
public class ProjectListDTO {
    private String name;
    private String group;
    private String url;
    private String key;
    private boolean starred;
    private String leadId;
    private String leadName;
    private String leadAvatar;
}

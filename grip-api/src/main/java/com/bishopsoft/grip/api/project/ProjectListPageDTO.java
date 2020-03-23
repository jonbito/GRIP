package com.bishopsoft.grip.api.project;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProjectListPageDTO {
    private List<ProjectListDTO> contents;
    private long total;
}

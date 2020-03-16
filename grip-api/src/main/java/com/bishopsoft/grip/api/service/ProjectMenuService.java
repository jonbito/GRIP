package com.bishopsoft.grip.api.service;

import com.bishopsoft.grip.api.dto.ProjectMenuDTO;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ProjectMenuService {
    public Set<ProjectMenuDTO> search(String search) {
        ProjectMenuDTO projectMenuDTO = new ProjectMenuDTO();
        projectMenuDTO.setIcon("mdi-home");
        projectMenuDTO.setId(1);
        projectMenuDTO.setName("Test Project");

        return Set.of();
    }

    public Set<ProjectMenuDTO> frequentProjects() {
        ProjectMenuDTO projectMenuDTO = new ProjectMenuDTO();
        projectMenuDTO.setIcon("mdi-home");
        projectMenuDTO.setId(1);
        projectMenuDTO.setName("Test Frequent Project");

        return Set.of();
    }
}

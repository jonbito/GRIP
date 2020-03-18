package com.bishopsoft.grip.api.projectmenu;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/project-menu")
public class ProjectMenuController {

    private final ProjectMenuService projectMenuService;

    public ProjectMenuController(ProjectMenuService projectMenuService) {
        this.projectMenuService = projectMenuService;
    }

    @GetMapping("/search/{search}")
    Set<ProjectMenuDTO> searchProjects(@PathVariable("search") String search) {
        return projectMenuService.search(search);
    }

    @GetMapping("/frequent")
    Set<ProjectMenuDTO> frequentProjects() {
        return projectMenuService.frequentProjects();
    }
}

package com.bishopsoft.grip.api.project;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/create")
    public long create(@Valid @RequestBody ProjectCreateBindingModel model) {
        return projectService.create(model);
    }

    @GetMapping("/hasAccess")
    public boolean hasAccess(@RequestParam String username, @RequestParam String projectKey) {
        return projectService.hasAccess(username, projectKey);
    }

}

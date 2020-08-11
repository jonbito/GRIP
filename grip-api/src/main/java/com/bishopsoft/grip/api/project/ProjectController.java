package com.bishopsoft.grip.api.project;

import com.bishopsoft.grip.api.infrastructure.dto.ListPageDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

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

    @GetMapping("/list")
    public ListPageDTO<ProjectListDTO> listProjects(
            @RequestParam long orgId,
            @RequestParam String sortBy,
            @RequestParam Optional<Boolean> sortDesc,
            @RequestParam int page,
            @RequestParam int itemsPerPage,
            @RequestParam String search,
            @RequestParam boolean starred
    ) {
        return projectService.listProjects(orgId, sortBy, sortDesc, page, itemsPerPage, search, starred);
    }

    @GetMapping("/keyExists/{key}")
    public boolean projectKeyExists(@PathVariable(value = "key") String key, @RequestParam long orgId) {
        return projectService.keyExists(key, orgId);
    }

    @PostMapping("/star/{projectId}/{star}")
    public boolean starProject(@PathVariable(value = "projectId") long projectId, @PathVariable(value = "star") boolean star) {
        return projectService.starProject(projectId, star);
    }
}

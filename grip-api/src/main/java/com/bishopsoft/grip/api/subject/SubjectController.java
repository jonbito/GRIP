package com.bishopsoft.grip.api.subject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subject")
public class SubjectController {
    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    public List<SubjectDto> list(@RequestParam Optional<String> search, @RequestParam Optional<Long> projectId) {
        return subjectService.list(projectId, search.orElse(""));
    }

    @PostMapping
    public SubjectDto create(@Valid @RequestBody SubjectCreateBindingModel model) {
        return subjectService.create(model.getProjectId(), model.getName(), model.getDescription());
    }
}

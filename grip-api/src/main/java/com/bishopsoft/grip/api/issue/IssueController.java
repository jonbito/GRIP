package com.bishopsoft.grip.api.issue;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/issue")
public class IssueController {
    private final IssueService issueService;

    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @PostMapping
    public IssueDto create(@Valid @RequestBody IssueCreateBindingModel model) {
        return issueService.create(model);
    }

    @GetMapping
    public List<IssueDto> list(@RequestParam Optional<Long> projectId) {
        return issueService.list(projectId);
    }

    @GetMapping("/{issueId}")
    public IssueDetailDto get(@PathVariable long issueId) {
        return issueService.get(issueId);
    }

    @PatchMapping("/{issueId}")
    public IssueDetailDto patch(@PathVariable long issueId, @Valid @RequestBody IssuePatchBindingModel model) {
        return issueService.patch(issueId, model);
    }
}

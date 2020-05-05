package com.bishopsoft.grip.api.agent;

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
@RequestMapping("/agent")
public class AgentController {
    private final AgentService agentService;

    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    @GetMapping
    public List<AgentDto> list(@RequestParam Optional<String> search, @RequestParam Optional<Long> projectId) {
        return agentService.list(projectId, search.orElse(""));
    }

    @PostMapping
    public AgentDto create(@Valid @RequestBody AgentCreateBindingModel model) {
        return agentService.create(model.getProjectId(), model.getName(), model.getDescription());
    }
}

package com.bishopsoft.grip.api.goal;

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
@RequestMapping("/goal")
public class GoalController {
    private final GoalService goalService;

    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @PostMapping
    public GoalDto create(@Valid @RequestBody GoalCreateBindingModel model) {
        return goalService.create(model);
    }

    @GetMapping
    public List<GoalDto> list(@RequestParam Optional<String> search, @RequestParam Optional<Long> projectId) {
        return goalService.list(search, projectId);
    }
}

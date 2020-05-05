package com.bishopsoft.grip.api.rule;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/rule")
public class RuleController {
    private final RuleService ruleService;

    public RuleController(RuleService ruleService) {
        this.ruleService = ruleService;
    }

    @PostMapping
    public RuleDto create(@Valid @RequestBody RuleCreateBindingModel model) {
        return ruleService.create(model);
    }

    @GetMapping
    public List<RuleDto> list(@RequestParam Optional<Long> goalId) {
        return ruleService.list(goalId);
    }

    @PostMapping("/{ruleId}/complete/{completed}")
    public ResponseEntity<String> completeRule(@PathVariable(value = "ruleId") long ruleId, @PathVariable(value = "completed") boolean completed) {
        ruleService.completeRule(ruleId, completed);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

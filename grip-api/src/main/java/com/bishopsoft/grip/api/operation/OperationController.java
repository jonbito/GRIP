package com.bishopsoft.grip.api.operation;

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
@RequestMapping("/operation")
public class OperationController {
    private final OperationService operationService;

    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @GetMapping
    public List<OperationDto> list(@RequestParam Optional<String> search, @RequestParam Optional<Long> projectId) {
        return operationService.list(projectId, search.orElse(""));
    }

    @PostMapping
    public OperationDto create(@Valid @RequestBody OperationCreateBindingModel model) {
        return operationService.create(model.getProjectId(), model.getName(), model.getDescription());
    }
}

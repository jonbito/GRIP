package com.bishopsoft.grip.api.org;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/org")
public class OrgController {
    private final OrgService orgService;

    public OrgController(OrgService orgService) {
        this.orgService = orgService;
    }

    @GetMapping("/urlExists/{name}")
    public boolean urlExists(@PathVariable String name) {
        return orgService.urlExists(name);
    }

    @PostMapping("/create")
    public OrgCreateDTO create(@Valid @RequestBody OrgCreateBindingModel model) {
        return orgService.create(model);
    }

    @GetMapping("/{url}")
    public OrgDetailsDTO details(@PathVariable String url) {
        return orgService.details(url);
    }
}

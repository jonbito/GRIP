package com.bishopsoft.grip.api.rule;

import com.bishopsoft.grip.api.infrastructure.exception.HttpException;
import com.bishopsoft.grip.api.infrastructure.model.Goal;
import com.bishopsoft.grip.api.infrastructure.model.Project;
import com.bishopsoft.grip.api.infrastructure.model.RoleEnum;
import com.bishopsoft.grip.api.infrastructure.model.Rule;
import com.bishopsoft.grip.api.infrastructure.model.UserAccount;
import com.bishopsoft.grip.api.infrastructure.repository.GoalRepository;
import com.bishopsoft.grip.api.infrastructure.repository.ProjectRepository;
import com.bishopsoft.grip.api.infrastructure.repository.RuleRepository;
import com.bishopsoft.grip.api.infrastructure.security.LoggedInUser;
import com.bishopsoft.grip.api.permission.PermissionService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RuleService {
    private final LoggedInUser loggedInUser;
    private final GoalRepository goalRepository;
    private final RuleRepository ruleRepository;
    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;
    private final PermissionService permissionService;
    private final EntityManager entityManager;

    public RuleService(LoggedInUser loggedInUser, GoalRepository goalRepository, RuleRepository ruleRepository, ProjectRepository projectRepository, ModelMapper modelMapper, PermissionService permissionService, EntityManager entityManager) {
        this.loggedInUser = loggedInUser;
        this.goalRepository = goalRepository;
        this.ruleRepository = ruleRepository;
        this.projectRepository = projectRepository;
        this.modelMapper = modelMapper;
        this.permissionService = permissionService;
        this.entityManager = entityManager;
    }

    @Transactional
    public RuleDto create(RuleCreateBindingModel model) {
        Goal goal = goalRepository.findById(model.getGoalId()).orElseThrow(() -> new HttpException("Goal not found", HttpStatus.BAD_REQUEST));
        permissionService.assertProjectRoleForLoggedInUser(goal.getProject().getId(), RoleEnum.REPORTER);
        Project project = projectRepository.findById(goal.getProject().getId()).orElseThrow(() -> new HttpException("Could not find project", HttpStatus.INTERNAL_SERVER_ERROR));
        Rule rule = new Rule();
        rule.setGoal(goal);
        rule.setName(model.getName());
        rule.setDescription(model.getDescription());
        rule.setNiceId(project.getRuleIncrement());
        ruleRepository.save(rule);
        project.setRuleIncrement(project.getRuleIncrement() + 1);
        return modelMapper.map(rule, RuleDto.class);
    }

    public List<RuleDto> list(Optional<Long> goalId) {
        if(goalId.isPresent()) {
            Goal goal = goalRepository.findById(goalId.get()).orElseThrow(() -> new HttpException("Goal not found", HttpStatus.BAD_REQUEST));
            permissionService.assertProjectRoleForLoggedInUser(goal.getProject().getId(), RoleEnum.REPORTER);
            List<Rule> rules = ruleRepository.findByGoal_Id(goalId.get());
            return rules.stream().map(r -> {
                RuleDto dto = modelMapper.map(r, RuleDto.class);
                dto.setGoalId(goalId.get());
                return dto;
            }).collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    @Transactional
    public void completeRule(long ruleId, boolean completed) {
        Rule rule = ruleRepository.findById(ruleId).orElseThrow(() -> new HttpException("Rule not found", HttpStatus.BAD_REQUEST));
        Goal goal = goalRepository.findById(rule.getGoal().getId()).orElseThrow(() -> new HttpException("Goal not found", HttpStatus.BAD_REQUEST));
        permissionService.assertProjectRoleForLoggedInUser(goal.getProject().getId(), RoleEnum.REPORTER);
        if(completed) {
            rule.setCompleteDate(new Date());
            UserAccount loggedInAccount = entityManager.getReference(UserAccount.class, loggedInUser.getId());
            rule.setCompletedBy(loggedInAccount);
        } else {
            rule.setCompletedBy(null);
            rule.setCompleteDate(null);
        }
    }
}

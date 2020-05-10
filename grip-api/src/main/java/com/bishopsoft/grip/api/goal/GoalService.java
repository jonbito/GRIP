package com.bishopsoft.grip.api.goal;

import com.bishopsoft.grip.api.infrastructure.exception.HttpException;
import com.bishopsoft.grip.api.infrastructure.model.Goal;
import com.bishopsoft.grip.api.infrastructure.model.Issue;
import com.bishopsoft.grip.api.infrastructure.model.RoleEnum;
import com.bishopsoft.grip.api.infrastructure.model.UserAccount;
import com.bishopsoft.grip.api.infrastructure.repository.GoalRepository;
import com.bishopsoft.grip.api.infrastructure.repository.IssueRepository;
import com.bishopsoft.grip.api.infrastructure.security.LoggedInUser;
import com.bishopsoft.grip.api.permission.PermissionService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GoalService {
    private final LoggedInUser loggedInUser;
    private final GoalRepository goalRepository;
    private final IssueRepository issueRepository;
    private final ModelMapper modelMapper;
    private final PermissionService permissionService;
    private final EntityManager entityManager;

    public GoalService(LoggedInUser loggedInUser, GoalRepository goalRepository, IssueRepository issueRepository, ModelMapper modelMapper, PermissionService permissionService, EntityManager entityManager) {
        this.loggedInUser = loggedInUser;
        this.goalRepository = goalRepository;
        this.issueRepository = issueRepository;
        this.modelMapper = modelMapper;
        this.permissionService = permissionService;
        this.entityManager = entityManager;
    }

    @Transactional
    public GoalDto create(GoalCreateBindingModel model) {
        long projectId;
        if(model.getGoalId() != null) {
            Goal goal = goalRepository.findById(model.getGoalId()).orElseThrow(() -> new HttpException("Goal not found", HttpStatus.BAD_REQUEST));
            projectId = goal.getIssue().getProject().getId();
        } else if(model.getIssueId() != null) {
            Issue issue = issueRepository.findById(model.getIssueId()).orElseThrow(() -> new HttpException("Issue not found", HttpStatus.BAD_REQUEST));
            projectId = issue.getProject().getId();
        } else {
            throw new HttpException("Issue ID or Goal ID is required", HttpStatus.BAD_REQUEST);
        }

        permissionService.assertProjectRoleForLoggedInUser(projectId, RoleEnum.REPORTER);
        Issue issue = issueRepository.findById(model.getIssueId()).orElseThrow(() -> new HttpException("Issue not found", HttpStatus.BAD_REQUEST));
        Goal goal = new Goal();
        goal.setNiceId(issue.getProject().getGoalIncrement());
        goal.setIssue(issue);
        goal.setSize(model.getSize());
        goal.setSummary(model.getSummary());
        goal.setDescription(model.getDescription());
        goal.setCreator(entityManager.getReference(UserAccount.class, loggedInUser.getId()));
        if(model.getGoalId() != null) {
            goal.setParent(entityManager.getReference(Goal.class, model.getGoalId()));
        }
        goalRepository.save(goal);
        issue.getProject().setGoalIncrement(issue.getProject().getGoalIncrement() + 1);

        return mapGoalToGoalDto(goal);
    }

    private GoalDto mapGoalToGoalDto(Goal goal) {
        GoalDto dto = modelMapper.map(goal, GoalDto.class);
        dto.setChildren(modelMapper.map(goal.getChildren(), new TypeToken<List<GoalDto>>() {
        }.getType()));
        return dto;
    }

    public List<GoalDto> list(Optional<String> search, Optional<Long> projectId) {
        if(projectId.isPresent()) {
            permissionService.assertProjectRoleForLoggedInUser(projectId.get(), RoleEnum.REPORTER);
            List<Goal> goals = goalRepository.findByIssueProject_IdAndParentIsNull(projectId.get());
            return goals.stream().map(this::mapGoalToGoalDto).collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }
}

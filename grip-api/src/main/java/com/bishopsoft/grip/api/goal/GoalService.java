package com.bishopsoft.grip.api.goal;

import com.bishopsoft.grip.api.agent.AgentDto;
import com.bishopsoft.grip.api.infrastructure.exception.HttpException;
import com.bishopsoft.grip.api.infrastructure.model.Agent;
import com.bishopsoft.grip.api.infrastructure.model.Goal;
import com.bishopsoft.grip.api.infrastructure.model.Operation;
import com.bishopsoft.grip.api.infrastructure.model.Project;
import com.bishopsoft.grip.api.infrastructure.model.RoleEnum;
import com.bishopsoft.grip.api.infrastructure.model.Subject;
import com.bishopsoft.grip.api.infrastructure.repository.GoalRepository;
import com.bishopsoft.grip.api.infrastructure.repository.ProjectRepository;
import com.bishopsoft.grip.api.infrastructure.security.LoggedInUser;
import com.bishopsoft.grip.api.operation.OperationDto;
import com.bishopsoft.grip.api.permission.PermissionService;
import com.bishopsoft.grip.api.subject.SubjectDto;
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
    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;
    private final PermissionService permissionService;
    private final EntityManager entityManager;

    public GoalService(LoggedInUser loggedInUser, GoalRepository goalRepository, ProjectRepository projectRepository, ModelMapper modelMapper, PermissionService permissionService, EntityManager entityManager) {
        this.loggedInUser = loggedInUser;
        this.goalRepository = goalRepository;
        this.projectRepository = projectRepository;
        this.modelMapper = modelMapper;
        this.permissionService = permissionService;
        this.entityManager = entityManager;
    }

    @Transactional
    public GoalDto create(GoalCreateBindingModel model) {
        long projectId;
        if(model.getGoalId() != null) {
            Goal goal = goalRepository.findById(model.getGoalId()).orElseThrow(() -> new HttpException("Goal not found", HttpStatus.BAD_REQUEST));
            projectId = goal.getProject().getId();
        } else {
            projectId = model.getProjectId();
        }
        permissionService.assertProjectRoleForLoggedInUser(projectId, RoleEnum.REPORTER);
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new HttpException("Could not find project", HttpStatus.INTERNAL_SERVER_ERROR));
        Goal goal = new Goal();
        goal.setNiceId(project.getGoalIncrement());
        goal.setAgent(entityManager.getReference(Agent.class, model.getAgentId()));
        goal.setProject(project);
        goal.setSubject(entityManager.getReference(Subject.class, model.getSubjectId()));
        goal.setOperation(entityManager.getReference(Operation.class, model.getOperationId()));
        goal.setSize(model.getSize());
        if(model.getGoalId() != null) {
            goal.setParent(entityManager.getReference(Goal.class, model.getGoalId()));
        }
        goalRepository.save(goal);
        project.setGoalIncrement(project.getGoalIncrement() + 1);

        return mapGoalToGoalDto(goal);
    }

    private GoalDto mapGoalToGoalDto(Goal goal) {
        GoalDto dto = modelMapper.map(goal, GoalDto.class);
        dto.setAgent(modelMapper.map(goal.getAgent(), AgentDto.class));
        dto.setOperation(modelMapper.map(goal.getOperation(), OperationDto.class));
        dto.setSubject(modelMapper.map(goal.getSubject(), SubjectDto.class));
        dto.setParent(modelMapper.map(goal.getParent(), GoalDto.class));
        dto.setChildren(modelMapper.map(goal.getChildren(), new TypeToken<List<GoalDto>>() {
        }.getType()));
        return dto;
    }

    public List<GoalDto> list(Optional<String> search, Optional<Long> projectId) {
        if(projectId.isPresent()) {
            permissionService.assertProjectRoleForLoggedInUser(projectId.get(), RoleEnum.REPORTER);
            List<Goal> goals = goalRepository.findByProjectIdAndParentIsNull(projectId.get());
            return goals.stream().map(this::mapGoalToGoalDto).collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }
}

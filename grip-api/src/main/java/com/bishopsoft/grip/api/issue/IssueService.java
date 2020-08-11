package com.bishopsoft.grip.api.issue;

import com.bishopsoft.grip.api.infrastructure.ModelPatcher;
import com.bishopsoft.grip.api.infrastructure.exception.HttpException;
import com.bishopsoft.grip.api.infrastructure.model.Issue;
import com.bishopsoft.grip.api.infrastructure.model.Project;
import com.bishopsoft.grip.api.infrastructure.model.RoleEnum;
import com.bishopsoft.grip.api.infrastructure.repository.IssueRepository;
import com.bishopsoft.grip.api.infrastructure.repository.ProjectRepository;
import com.bishopsoft.grip.api.infrastructure.security.LoggedInUser;
import com.bishopsoft.grip.api.permission.PermissionService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IssueService {
    private final LoggedInUser loggedInUser;
    private final IssueRepository issueRepository;
    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;
    private final PermissionService permissionService;
    private final EntityManager entityManager;

    public IssueService(LoggedInUser loggedInUser, IssueRepository issueRepository, ProjectRepository projectRepository, ModelMapper modelMapper, PermissionService permissionService, EntityManager entityManager) {
        this.loggedInUser = loggedInUser;
        this.issueRepository = issueRepository;
        this.projectRepository = projectRepository;
        this.modelMapper = modelMapper;
        this.permissionService = permissionService;
        this.entityManager = entityManager;
    }

    @Transactional
    public IssueDto create(IssueCreateBindingModel model) {
        permissionService.assertProjectRoleForLoggedInUser(model.getProjectId(), RoleEnum.REPORTER);
        Project project = projectRepository.findById(model.getProjectId()).orElseThrow(() -> new HttpException("Could not find project", HttpStatus.INTERNAL_SERVER_ERROR));
        Issue issue = new Issue();
        issue.setSummary(model.getSummary());
        issue.setDescription(model.getDescription());
        issue.setNiceId(project.getIssueIncrement());
        issueRepository.save(issue);
        project.setIssueIncrement(project.getIssueIncrement() + 1);
        return modelMapper.map(issue, IssueDto.class);
    }

    public List<IssueDto> list(Optional<Long> projectId) {
        if(projectId.isPresent()) {
            permissionService.assertProjectRoleForLoggedInUser(projectId.get(), RoleEnum.REPORTER);
            List<Issue> issues = issueRepository.findByProjectId(projectId.get(), Sort.by(Sort.Direction.ASC, "id"));
            return issues.stream().map(i -> {
                IssueDto dto = modelMapper.map(i, IssueDto.class);
                dto.setProjectId(i.getProject().getId());
                return dto;
            }).collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    public IssueDetailDto get(long issueId) {
        Issue issue = issueRepository.findById(issueId).orElseThrow(() -> new HttpException("Issue not found", HttpStatus.BAD_REQUEST));
        permissionService.assertProjectRoleForLoggedInUser(issue.getProject().getId(), RoleEnum.REPORTER);
        return modelMapper.map(issue, IssueDetailDto.class);
    }

    @Transactional
    public IssueDetailDto patch(long issueId, @Valid IssuePatchBindingModel updates) {
        Issue issue = issueRepository.findById(issueId).orElseThrow(() -> new HttpException("Issue not found", HttpStatus.BAD_REQUEST));
        permissionService.assertProjectRoleForLoggedInUser(issue.getProject().getId(), RoleEnum.REPORTER);

        ModelPatcher.patch(issue, updates);

        return modelMapper.map(issue, IssueDetailDto.class);
    }
}

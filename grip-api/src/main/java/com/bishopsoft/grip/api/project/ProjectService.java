package com.bishopsoft.grip.api.project;

import com.bishopsoft.grip.api.infrastructure.exception.HttpException;
import com.bishopsoft.grip.api.infrastructure.model.Project;
import com.bishopsoft.grip.api.infrastructure.model.RoleEnum;
import com.bishopsoft.grip.api.infrastructure.model.UserAccount;
import com.bishopsoft.grip.api.infrastructure.model.UserPermissionProject;
import com.bishopsoft.grip.api.infrastructure.repository.ProjectRepository;
import com.bishopsoft.grip.api.infrastructure.repository.UserPermissionProjectRepository;
import com.bishopsoft.grip.api.infrastructure.repository.UserRepository;
import com.bishopsoft.grip.api.infrastructure.security.LoggedInUser;
import com.bishopsoft.grip.api.upload.UploadService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserPermissionProjectRepository userPermissionProjectRepository;
    private final UserRepository userRepository;
    private final LoggedInUser loggedInUser;
    private final UploadService uploadService;

    public ProjectService(ProjectRepository projectRepository, UserPermissionProjectRepository userPermissionProjectRepository, UserRepository userRepository, LoggedInUser loggedInUser, UploadService uploadService) {
        this.projectRepository = projectRepository;
        this.userPermissionProjectRepository = userPermissionProjectRepository;
        this.userRepository = userRepository;
        this.loggedInUser = loggedInUser;
        this.uploadService = uploadService;
    }

    @Transactional
    public long create(ProjectCreateBindingModel projectCreateBindingModel) {
        UserAccount user = userRepository.findById(loggedInUser.getId()).orElseThrow(() -> new HttpException("Couldn't find user", HttpStatus.INTERNAL_SERVER_ERROR));
        Optional<UserPermissionProject> findByKey = userPermissionProjectRepository.findByUser_IdAndProjectKey(user.getId(), projectCreateBindingModel.getKey());
        if(findByKey.isPresent()) {
            throw new HttpException("Project key already exists", HttpStatus.BAD_REQUEST);
        }
        Project project = createProject(projectCreateBindingModel, user);
        createOwnerPermission(project, user);
        return project.getId();
    }

    public boolean hasAccess(String username, String projectKey) {
        Optional<UserPermissionProject> userPermissionProjectOptional = userPermissionProjectRepository.findByUserUsernameIgnoreCaseAndProjectKeyIgnoreCase(username, projectKey);
        if(userPermissionProjectOptional.isPresent()) {
            return true;
        }
        throw new HttpException("Not Found", HttpStatus.NOT_FOUND);
    }

    public ProjectListPageDTO listProjects() {
        Page<UserPermissionProject> userPermissionProjects = userPermissionProjectRepository.findByUser_Id(loggedInUser.getId(), PageRequest.of(0, 10, Sort.by("project.name").ascending()));
        List<ProjectListDTO> projectDtos = userPermissionProjects.getContent().stream()
                .map(u -> {
                    ProjectListDTO dto = new ProjectListDTO();
                    dto.setKey(u.getProject().getKey());
                    dto.setName(u.getProject().getName());
                    dto.setGroup(u.getUser().getFirstName() + " " + u.getUser().getLastName());
                    dto.setLeadId(u.getProject().getLead().getId().toString());
                    dto.setLeadName(u.getProject().getLead().getFirstName() + " " + u.getProject().getLead().getLastName());
                    dto.setUrl("/");
                    dto.setStarred(false);
                    dto.setLeadAvatar(uploadService.getUserAvatar(u.getUser().getId()));
                    return dto;
                })
                .collect(Collectors.toList());
        return ProjectListPageDTO.builder().contents(projectDtos).total(userPermissionProjects.getTotalElements()).build();
    }

    private Project createProject(ProjectCreateBindingModel projectCreateBindingModel, UserAccount user) {
        Project project = new Project();
        project.setKey(projectCreateBindingModel.getKey());
        project.setName(projectCreateBindingModel.getName());
        project.setLead(user);

        projectRepository.save(project);
        return project;
    }

    private void createOwnerPermission(Project project, UserAccount user) {
        UserPermissionProject userPermissionProject = new UserPermissionProject();
        userPermissionProject.setRole(RoleEnum.OWNER);
        userPermissionProject.setProject(project);
        userPermissionProject.setUser(user);

        userPermissionProjectRepository.save(userPermissionProject);
    }
}

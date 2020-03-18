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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserPermissionProjectRepository userPermissionProjectRepository;
    private final UserRepository userRepository;
    private final LoggedInUser loggedInUser;

    public ProjectService(ProjectRepository projectRepository, UserPermissionProjectRepository userPermissionProjectRepository, UserRepository userRepository, LoggedInUser loggedInUser) {
        this.projectRepository = projectRepository;
        this.userPermissionProjectRepository = userPermissionProjectRepository;
        this.userRepository = userRepository;
        this.loggedInUser = loggedInUser;
    }

    @Transactional
    public long create(ProjectCreateBindingModel projectCreateBindingModel) {
        Project project = createProject(projectCreateBindingModel);
        createOwnerPermission(project);
        return project.getId();
    }

    private Project createProject(ProjectCreateBindingModel projectCreateBindingModel) {
        Project project = new Project();
        project.setKey(projectCreateBindingModel.getKey());
        project.setName(projectCreateBindingModel.getName());

        projectRepository.save(project);
        return project;
    }

    private void createOwnerPermission(Project project) {
        UserAccount user = userRepository.findById(loggedInUser.getId()).orElseThrow(() -> new HttpException("Couldn't find user", HttpStatus.INTERNAL_SERVER_ERROR));

        UserPermissionProject userPermissionProject = new UserPermissionProject();
        userPermissionProject.setRole(RoleEnum.OWNER);
        userPermissionProject.setProject(project);
        userPermissionProject.setUser(user);

        userPermissionProjectRepository.save(userPermissionProject);
    }
}

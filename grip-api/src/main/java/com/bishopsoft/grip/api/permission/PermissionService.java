package com.bishopsoft.grip.api.permission;

import com.bishopsoft.grip.api.infrastructure.exception.HttpException;
import com.bishopsoft.grip.api.infrastructure.model.Project;
import com.bishopsoft.grip.api.infrastructure.model.RoleEnum;
import com.bishopsoft.grip.api.infrastructure.model.UserPermissionGroup;
import com.bishopsoft.grip.api.infrastructure.model.UserPermissionGroupId;
import com.bishopsoft.grip.api.infrastructure.model.UserPermissionProject;
import com.bishopsoft.grip.api.infrastructure.model.UserPermissionProjectId;
import com.bishopsoft.grip.api.infrastructure.repository.ProjectRepository;
import com.bishopsoft.grip.api.infrastructure.repository.UserPermissionGroupRepository;
import com.bishopsoft.grip.api.infrastructure.repository.UserPermissionProjectRepository;
import com.bishopsoft.grip.api.infrastructure.security.LoggedInUser;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {

    private final UserPermissionProjectRepository userPermissionProjectRepository;
    private final UserPermissionGroupRepository userPermissionGroupRepository;
    private final ProjectRepository projectRepository;
    private final LoggedInUser loggedInUser;

    public PermissionService(UserPermissionProjectRepository userPermissionProjectRepository, UserPermissionGroupRepository userPermissionGroupRepository, ProjectRepository projectRepository, LoggedInUser loggedInUser) {
        this.userPermissionProjectRepository = userPermissionProjectRepository;
        this.userPermissionGroupRepository = userPermissionGroupRepository;
        this.projectRepository = projectRepository;
        this.loggedInUser = loggedInUser;
    }

    public void assertProjectRoleForLoggedInUser(long projectId, RoleEnum role) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new HttpException("Project not found", HttpStatus.BAD_REQUEST));

        // project is not in a group
        if(project.getOwnerGroup() == null) {
            UserPermissionProjectId userPermissionProjectId = new UserPermissionProjectId();
            userPermissionProjectId.setProject(projectId);
            userPermissionProjectId.setUser(loggedInUser.getId());
            UserPermissionProject userPermissionProject = userPermissionProjectRepository.findById(userPermissionProjectId).orElseThrow(() -> new HttpException("User does not belong to project", HttpStatus.FORBIDDEN));
            RoleEnum currentRole = userPermissionProject.getRole();
            if(currentRole.ordinal() < role.ordinal()) {
                throw new HttpException("User does not have permission", HttpStatus.FORBIDDEN);
            }
        } else {
            UserPermissionGroupId userPermissionGroupId = new UserPermissionGroupId();
            userPermissionGroupId.setGroup(project.getOwnerGroup().getId());
            userPermissionGroupId.setUser(loggedInUser.getId());
            UserPermissionGroup userPermissionGroup = userPermissionGroupRepository.findById(userPermissionGroupId).orElseThrow(() -> new HttpException("User does not belong to group", HttpStatus.FORBIDDEN));
            RoleEnum currentRole = userPermissionGroup.getRole();
            if(currentRole.ordinal() < role.ordinal()) {
                throw new HttpException("User does not have permission", HttpStatus.FORBIDDEN);
            }
        }
    }

    public void assertGroupRoleForLoggedInUser(long groupId, RoleEnum role) {
        UserPermissionGroupId userPermissionGroupId = new UserPermissionGroupId();
        userPermissionGroupId.setGroup(groupId);
        userPermissionGroupId.setUser(loggedInUser.getId());
        UserPermissionGroup userPermissionGroup = userPermissionGroupRepository.findById(userPermissionGroupId).orElseThrow(() -> new HttpException("User does not belong to group", HttpStatus.FORBIDDEN));
        RoleEnum currentRole = userPermissionGroup.getRole();
        if(currentRole.ordinal() < role.ordinal()) {
            throw new HttpException("User does not have permission", HttpStatus.FORBIDDEN);
        }
    }
}

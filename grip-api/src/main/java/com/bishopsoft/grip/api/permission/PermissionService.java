package com.bishopsoft.grip.api.permission;

import com.bishopsoft.grip.api.infrastructure.exception.HttpException;
import com.bishopsoft.grip.api.infrastructure.model.Project;
import com.bishopsoft.grip.api.infrastructure.model.RoleEnum;
import com.bishopsoft.grip.api.infrastructure.model.OrgUserPermission;
import com.bishopsoft.grip.api.infrastructure.model.OrgUserPermissionId;
import com.bishopsoft.grip.api.infrastructure.repository.ProjectRepository;
import com.bishopsoft.grip.api.infrastructure.repository.OrgUserPermissionRepository;
import com.bishopsoft.grip.api.infrastructure.security.LoggedInUser;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {

    private final OrgUserPermissionRepository orgUserPermissionRepository;
    private final ProjectRepository projectRepository;
    private final LoggedInUser loggedInUser;

    public PermissionService(OrgUserPermissionRepository orgUserPermissionRepository, ProjectRepository projectRepository, LoggedInUser loggedInUser) {
        this.orgUserPermissionRepository = orgUserPermissionRepository;
        this.projectRepository = projectRepository;
        this.loggedInUser = loggedInUser;
    }

    public void assertProjectRoleForLoggedInUser(long projectId, RoleEnum role) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new HttpException("Project not found", HttpStatus.BAD_REQUEST));
        assertOrgRoleForLoggedInUser(project.getOrg().getId(), role);
    }

    public void assertOrgRoleForLoggedInUser(long orgId, RoleEnum role) {
        OrgUserPermissionId orgUserPermissionId = new OrgUserPermissionId();
        orgUserPermissionId.setOrg(orgId);
        orgUserPermissionId.setUser(loggedInUser.getId());
        OrgUserPermission orgUserPermission = orgUserPermissionRepository.findById(orgUserPermissionId).orElseThrow(() -> new HttpException("User does not belong to org", HttpStatus.FORBIDDEN));
        RoleEnum currentRole = orgUserPermission.getRole();
        if(currentRole.ordinal() < role.ordinal()) {
            throw new HttpException("User does not have permission", HttpStatus.FORBIDDEN);
        }
    }
}

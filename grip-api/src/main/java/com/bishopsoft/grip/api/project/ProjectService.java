package com.bishopsoft.grip.api.project;

import com.bishopsoft.grip.api.file.FileService;
import com.bishopsoft.grip.api.infrastructure.dto.ListPageDTO;
import com.bishopsoft.grip.api.infrastructure.exception.HttpException;
import com.bishopsoft.grip.api.infrastructure.model.Project;
import com.bishopsoft.grip.api.infrastructure.model.RoleEnum;
import com.bishopsoft.grip.api.infrastructure.model.UserAccount;
import com.bishopsoft.grip.api.infrastructure.repository.ProjectRepository;
import com.bishopsoft.grip.api.infrastructure.repository.UserRepository;
import com.bishopsoft.grip.api.infrastructure.security.LoggedInUser;
import com.bishopsoft.grip.api.permission.PermissionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final LoggedInUser loggedInUser;
    private final PermissionService permissionService;
    private final FileService fileService;

    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository, LoggedInUser loggedInUser, PermissionService permissionService, FileService fileService) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.loggedInUser = loggedInUser;
        this.permissionService = permissionService;
        this.fileService = fileService;
    }

    @Transactional
    public long create(ProjectCreateBindingModel projectCreateBindingModel) {
        projectCreateBindingModel.setKey(projectCreateBindingModel.getKey().toUpperCase());
        permissionService.assertOrgRoleForLoggedInUser(projectCreateBindingModel.getOrgId(), RoleEnum.REPORTER);
        if(projectRepository.existsByKeyAndOrg_Id(projectCreateBindingModel.getKey(), projectCreateBindingModel.getOrgId())) {
            throw new HttpException("Project key already exists", HttpStatus.BAD_REQUEST);
        }

        UserAccount user = userRepository.findById(loggedInUser.getId()).orElseThrow(() -> new HttpException("Couldn't find user", HttpStatus.INTERNAL_SERVER_ERROR));
        Project project = createProject(projectCreateBindingModel, user);
        return project.getId();
    }

    public ListPageDTO<ProjectListDTO> listProjects(long orgId, String sortBy, Optional<Boolean> sortDesc, int page, int itemsPerPage, String search, boolean starred) {
        permissionService.assertOrgRoleForLoggedInUser(orgId, RoleEnum.GUEST);

        Sort sort = sortBy.isBlank() ? Sort.by("name").ascending() : Sort.by(sortBy);
        sort = sortDesc.isPresent() && sortDesc.get() ? sort.descending() : sort.ascending();


        UserAccount user = userRepository.findById(loggedInUser.getId()).orElseThrow(() -> new HttpException("Couldn't find user", HttpStatus.INTERNAL_SERVER_ERROR));
        Page<Project> projects = starred ?
                projectRepository.searchByStarred(orgId, new HashSet<>(user.getStarredProjects()), search.replaceAll("\\s", ""), PageRequest.of(page - 1, itemsPerPage, sort))
                : projectRepository.search(orgId, search.replaceAll("\\s", ""), PageRequest.of(page - 1, itemsPerPage, sort));

        List<ProjectListDTO> projectDtos = projects.getContent().stream()
                .map(p -> {
                    ProjectListDTO dto = new ProjectListDTO();
                    dto.setId(p.getId());
                    dto.setKey(p.getKey());
                    dto.setName(p.getName());
                    dto.setLeadId(p.getLead().getId().toString());
                    dto.setLeadName(p.getLead().getFirstName() + " " + p.getLead().getLastName());
                    dto.setStarred(user.getStarredProjects().contains(p.getId()));
                    dto.setLeadAvatar(null);
                    return dto;
                })
                .collect(Collectors.toList());

        ListPageDTO<ProjectListDTO> ret = new ListPageDTO<>();
        ret.setContents(projectDtos);
        ret.setTotal(projects.getTotalElements());
        return ret;
    }

    @Transactional
    private Project createProject(ProjectCreateBindingModel projectCreateBindingModel, UserAccount user) {
        Project project = new Project();
        project.setKey(projectCreateBindingModel.getKey());
        project.setName(projectCreateBindingModel.getName());
        project.setLead(user);

        projectRepository.save(project);
        return project;
    }

    public boolean keyExists(String key, long orgId) {
        permissionService.assertOrgRoleForLoggedInUser(orgId, RoleEnum.REPORTER);
        return projectRepository.existsByKeyAndOrg_Id(key.toUpperCase(), orgId);
    }

    @Transactional
    public boolean starProject(long projectId, boolean star) {
        UserAccount user = userRepository.findById(loggedInUser.getId()).orElseThrow(() -> new HttpException("Couldn't find user", HttpStatus.INTERNAL_SERVER_ERROR));
        List<Long> starredProjects = user.getStarredProjects();
        if (star && starredProjects.contains(projectId)) {
            return false;
        }
        if (!star && !starredProjects.contains(projectId)) {
            return false;
        }

        if (star) {
            starredProjects.add(projectId);
        } else {
            starredProjects.remove(projectId);
        }

        return true;
    }
}

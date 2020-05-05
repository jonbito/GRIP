package com.bishopsoft.grip.api.subject;

import com.bishopsoft.grip.api.infrastructure.model.Project;
import com.bishopsoft.grip.api.infrastructure.model.RoleEnum;
import com.bishopsoft.grip.api.infrastructure.model.Subject;
import com.bishopsoft.grip.api.infrastructure.repository.SubjectRepository;
import com.bishopsoft.grip.api.infrastructure.security.LoggedInUser;
import com.bishopsoft.grip.api.permission.PermissionService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {
    private final LoggedInUser loggedInUser;
    private final SubjectRepository subjectRepository;
    private final ModelMapper modelMapper;
    private final PermissionService permissionService;
    private final EntityManager entityManager;

    public SubjectService(LoggedInUser loggedInUser, SubjectRepository subjectRepository, ModelMapper modelMapper, PermissionService permissionService, EntityManager entityManager) {
        this.loggedInUser = loggedInUser;
        this.subjectRepository = subjectRepository;
        this.modelMapper = modelMapper;
        this.permissionService = permissionService;
        this.entityManager = entityManager;
    }

    public List<SubjectDto> list(Optional<Long> projectId, String search) {
        if(projectId.isPresent()) {
            permissionService.assertProjectRoleForLoggedInUser(projectId.get(), RoleEnum.REPORTER);
            List<Subject> subjects = subjectRepository.findByProjectId(projectId.get());
            return modelMapper.map(subjects, new TypeToken<List<SubjectDto>>(){}.getType());
        } else {
            return new ArrayList<>();
        }
    }

    public SubjectDto create(long projectId, String name, String description) {
        permissionService.assertProjectRoleForLoggedInUser(projectId, RoleEnum.REPORTER);
        Subject subject = new Subject();
        Project project = entityManager.getReference(Project.class, projectId);
        subject.setProject(project);
        subject.setName(name);
        subject.setDescription(description);
        subjectRepository.save(subject);
        return modelMapper.map(subject, SubjectDto.class);
    }
}

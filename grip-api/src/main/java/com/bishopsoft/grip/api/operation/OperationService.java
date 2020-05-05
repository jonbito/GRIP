package com.bishopsoft.grip.api.operation;

import com.bishopsoft.grip.api.infrastructure.model.Operation;
import com.bishopsoft.grip.api.infrastructure.model.Project;
import com.bishopsoft.grip.api.infrastructure.model.RoleEnum;
import com.bishopsoft.grip.api.infrastructure.repository.OperationRepository;
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
public class OperationService {
    private final LoggedInUser loggedInUser;
    private final OperationRepository operationRepository;
    private final ModelMapper modelMapper;
    private final PermissionService permissionService;
    private final EntityManager entityManager;

    public OperationService(LoggedInUser loggedInUser, OperationRepository operationRepository, ModelMapper modelMapper, PermissionService permissionService, EntityManager entityManager) {
        this.loggedInUser = loggedInUser;
        this.operationRepository = operationRepository;
        this.modelMapper = modelMapper;
        this.permissionService = permissionService;
        this.entityManager = entityManager;
    }

    public List<OperationDto> list(Optional<Long> projectId, String search) {
        if(projectId.isPresent()) {
            permissionService.assertProjectRoleForLoggedInUser(projectId.get(), RoleEnum.REPORTER);
            List<Operation> operations = operationRepository.findByProjectId(projectId.get());
            return modelMapper.map(operations, new TypeToken<List<OperationDto>>(){}.getType());
        } else {
            return new ArrayList<>();
        }
    }

    public OperationDto create(long projectId, String name, String description) {
        permissionService.assertProjectRoleForLoggedInUser(projectId, RoleEnum.REPORTER);
        Operation operation = new Operation();
        Project project = entityManager.getReference(Project.class, projectId);
        operation.setProject(project);
        operation.setName(name);
        operation.setDescription(description);
        operationRepository.save(operation);
        return modelMapper.map(operation, OperationDto.class);
    }
}

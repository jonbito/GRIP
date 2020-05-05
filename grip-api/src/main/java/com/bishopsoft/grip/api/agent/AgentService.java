package com.bishopsoft.grip.api.agent;

import com.bishopsoft.grip.api.infrastructure.model.Agent;
import com.bishopsoft.grip.api.infrastructure.model.Project;
import com.bishopsoft.grip.api.infrastructure.model.RoleEnum;
import com.bishopsoft.grip.api.infrastructure.repository.AgentRepository;
import com.bishopsoft.grip.api.infrastructure.security.LoggedInUser;
import com.bishopsoft.grip.api.permission.PermissionService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AgentService {
    private final LoggedInUser loggedInUser;
    private final AgentRepository agentRepository;
    private final ModelMapper modelMapper;
    private final PermissionService permissionService;
    private final EntityManager entityManager;

    public AgentService(LoggedInUser loggedInUser, AgentRepository agentRepository, ModelMapper modelMapper, PermissionService permissionService, EntityManager entityManager) {
        this.loggedInUser = loggedInUser;
        this.agentRepository = agentRepository;
        this.modelMapper = modelMapper;
        this.permissionService = permissionService;
        this.entityManager = entityManager;
    }

    public List<AgentDto> list(Optional<Long> projectId, String search) {
        if(projectId.isPresent()) {
            permissionService.assertProjectRoleForLoggedInUser(projectId.get(), RoleEnum.REPORTER);
            List<Agent> agents = agentRepository.findByProjectId(projectId.get());
            return modelMapper.map(agents, new TypeToken<List<AgentDto>>(){}.getType());
        } else {
            return new ArrayList<>();
        }
    }

    @Transactional
    public AgentDto create(long projectId, String name, String description) {
        permissionService.assertProjectRoleForLoggedInUser(projectId, RoleEnum.REPORTER);
        Agent agent = new Agent();
        Project project = entityManager.getReference(Project.class, projectId);
        agent.setProject(project);
        agent.setName(name);
        agent.setDescription(description);
        agentRepository.save(agent);
        return modelMapper.map(agent, AgentDto.class);
    }
}

package com.bishopsoft.grip.api.org;

import com.bishopsoft.grip.api.infrastructure.exception.HttpException;
import com.bishopsoft.grip.api.infrastructure.model.Org;
import com.bishopsoft.grip.api.infrastructure.model.OrgUserPermission;
import com.bishopsoft.grip.api.infrastructure.model.RoleEnum;
import com.bishopsoft.grip.api.infrastructure.model.UserAccount;
import com.bishopsoft.grip.api.infrastructure.repository.OrgRepository;
import com.bishopsoft.grip.api.infrastructure.repository.OrgUserPermissionRepository;
import com.bishopsoft.grip.api.infrastructure.security.LoggedInUser;
import com.bishopsoft.grip.api.permission.PermissionService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Set;

@Service
public class OrgService {
    private final OrgRepository orgRepository;
    private final OrgUserPermissionRepository orgUserPermissionRepository;
    private final PermissionService permissionService;
    private final ModelMapper modelMapper;
    private final LoggedInUser loggedInUser;
    private final EntityManager entityManager;

    public OrgService(OrgRepository orgRepository, OrgUserPermissionRepository orgUserPermissionRepository, PermissionService permissionService, ModelMapper modelMapper, LoggedInUser loggedInUser, EntityManager entityManager) {
        this.orgRepository = orgRepository;
        this.orgUserPermissionRepository = orgUserPermissionRepository;
        this.permissionService = permissionService;
        this.modelMapper = modelMapper;
        this.loggedInUser = loggedInUser;
        this.entityManager = entityManager;
    }

    public boolean urlExists(String name) {
        String url = Org.convertNameToUrl(name);
        return orgRepository.existsByUrl(url);
    }

    @Transactional
    public OrgCreateDTO create(OrgCreateBindingModel model) {
        Org org = new Org();
        org.setName(model.getName());
        org.setEmail(model.getEmail());
        orgRepository.save(org);
        OrgUserPermission permission = new OrgUserPermission();
        permission.setOrg(org);
        permission.setUser(entityManager.getReference(UserAccount.class, loggedInUser.getId()));
        permission.setRole(RoleEnum.OWNER);
        orgUserPermissionRepository.save(permission);
        return modelMapper.map(org, OrgCreateDTO.class);
    }

    public OrgDetailsDTO details(String url) {
        Org org = orgRepository.findByUrl(url).orElseThrow(() -> new HttpException("Org not found", HttpStatus.NOT_FOUND));
        permissionService.assertOrgRoleForLoggedInUser(org.getId(), RoleEnum.GUEST);
        return modelMapper.map(org, OrgDetailsDTO.class);
    }
}

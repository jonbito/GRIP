package com.bishopsoft.grip.api.infrastructure.repository;

import com.bishopsoft.grip.api.infrastructure.model.OrgUserPermission;
import com.bishopsoft.grip.api.infrastructure.model.OrgUserPermissionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrgUserPermissionRepository extends JpaRepository<OrgUserPermission, OrgUserPermissionId> {
}

package com.bishopsoft.grip.api.infrastructure.repository;

import com.bishopsoft.grip.api.infrastructure.model.UserPermissionGroup;
import com.bishopsoft.grip.api.infrastructure.model.UserPermissionGroupId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPermissionGroupRepository extends JpaRepository<UserPermissionGroup, UserPermissionGroupId> {
}

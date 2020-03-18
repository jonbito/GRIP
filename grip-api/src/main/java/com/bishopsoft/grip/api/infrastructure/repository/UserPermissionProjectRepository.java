package com.bishopsoft.grip.api.infrastructure.repository;

import com.bishopsoft.grip.api.infrastructure.model.UserPermissionProject;
import com.bishopsoft.grip.api.infrastructure.model.UserPermissionProjectId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPermissionProjectRepository extends JpaRepository<UserPermissionProject, UserPermissionProjectId> {
}

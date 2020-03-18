package com.bishopsoft.grip.api.infrastructure.repository;

import com.bishopsoft.grip.api.infrastructure.model.UserPermissionProject;
import com.bishopsoft.grip.api.infrastructure.model.UserPermissionProjectId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPermissionProjectRepository extends JpaRepository<UserPermissionProject, UserPermissionProjectId> {
    Optional<UserPermissionProject> findByUserUsernameIgnoreCaseAndProjectKeyIgnoreCase(String username, String projectKey);
}

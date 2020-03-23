package com.bishopsoft.grip.api.infrastructure.repository;

import com.bishopsoft.grip.api.infrastructure.model.UserPermissionProject;
import com.bishopsoft.grip.api.infrastructure.model.UserPermissionProjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserPermissionProjectRepository extends JpaRepository<UserPermissionProject, UserPermissionProjectId> {
    Optional<UserPermissionProject> findByUserUsernameIgnoreCaseAndProjectKeyIgnoreCase(String username, String projectKey);
    Page<UserPermissionProject> findByUser_Id(UUID id, Pageable pageable);
    Optional<UserPermissionProject> findByUser_IdAndProjectKey(UUID id, String key);
}

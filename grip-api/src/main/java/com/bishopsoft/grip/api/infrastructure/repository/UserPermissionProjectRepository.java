package com.bishopsoft.grip.api.infrastructure.repository;

import com.bishopsoft.grip.api.infrastructure.model.UserPermissionProject;
import com.bishopsoft.grip.api.infrastructure.model.UserPermissionProjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserPermissionProjectRepository extends JpaRepository<UserPermissionProject, UserPermissionProjectId> {
    Optional<UserPermissionProject> findByUserUsernameIgnoreCaseAndProjectKeyIgnoreCase(String username, String projectKey);
    Optional<UserPermissionProject> findByUser_IdAndProjectKey(UUID id, String key);
    boolean existsByUser_IdAndProjectKey(UUID id, String key);

    @EntityGraph(attributePaths = {"user", "project"})
    @Query("select u from UserPermissionProject u where u.user.id = :id and (lower(function('replace', u.project.name, ' ', '')) like lower(concat('%', :search, '%')) or lower(u.project.key) like lower(concat('%', :search, '%')) or lower(concat(u.project.lead.firstName, u.project.lead.lastName)) like lower(concat('%', :search, '%')))")
    Page<UserPermissionProject> searchByUser(
            @Param(value = "id") UUID id,
            @Param(value = "search") String search,
            Pageable pageable);

    @EntityGraph(attributePaths = {"user", "project"})
    @Query("select u from UserPermissionProject u where u.user.id = :id and u.project.id = function('ANY', u.user.starredProjects) and (lower(function('replace', u.project.name, ' ', '')) like lower(concat('%', :search, '%')) or lower(u.project.key) like lower(concat('%', :search, '%')) or lower(concat(u.project.lead.firstName, u.project.lead.lastName)) like lower(concat('%', :search, '%')))")
    Page<UserPermissionProject> searchByUserAndStarred(
            @Param(value = "id") UUID id,
            @Param(value = "search") String search,
            Pageable pageable);
}

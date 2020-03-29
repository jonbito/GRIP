package com.bishopsoft.grip.api.infrastructure.repository;

import com.bishopsoft.grip.api.infrastructure.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    boolean existsByKeyAndOwnerGroup_Id(String key, long ownerGroupId);
    boolean existsByKeyAndOwnerUser_Id(String key, UUID userId);
}

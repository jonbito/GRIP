package com.bishopsoft.grip.api.infrastructure.repository;

import com.bishopsoft.grip.api.infrastructure.model.Release;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReleaseRepository extends JpaRepository<Release, Long> {
    List<Release> findByProjectId(long projectId);
}

package com.bishopsoft.grip.api.infrastructure.repository;

import com.bishopsoft.grip.api.infrastructure.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    boolean existsByKeyAndOrg_Id(String key, long orgId);

    @EntityGraph(attributePaths = {"org", "lead"})
    @Query("select p from Project p where p.org.id = :orgId and (lower(function('replace', p.name, ' ', '')) like lower(concat('%', :search, '%')) or lower(p.key) like lower(concat('%', :search, '%')) or lower(concat(p.lead.firstName, p.lead.lastName)) like lower(concat('%', :search, '%')))")
    Page<Project> search(
            @Param(value = "orgId") long orgId,
            @Param(value = "search") String search,
            Pageable pageable);

    @EntityGraph(attributePaths = {"org", "lead"})
    @Query("select p from Project p where p.org.id = :orgId and p.id in :starredIds and (lower(function('replace', p.name, ' ', '')) like lower(concat('%', :search, '%')) or lower(p.key) like lower(concat('%', :search, '%')) or lower(concat(p.lead.firstName, p.lead.lastName)) like lower(concat('%', :search, '%')))")
    Page<Project> searchByStarred(
            @Param(value = "orgId") long orgId,
            @Param(value = "starredIds") Set<Long> starredIds,
            @Param(value = "search") String search,
            Pageable pageable);
}

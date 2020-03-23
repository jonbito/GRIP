package com.bishopsoft.grip.api.infrastructure.repository;

import com.bishopsoft.grip.api.infrastructure.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
}

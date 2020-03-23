package com.bishopsoft.grip.api.infrastructure.repository;

import com.bishopsoft.grip.api.infrastructure.model.Upload;
import com.bishopsoft.grip.api.infrastructure.model.UploadTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UploadRepository extends JpaRepository<Upload, Long> {
    Optional<Upload> findByUploadTypeAndReferencedId(UploadTypeEnum uploadType, String referencedId);
}

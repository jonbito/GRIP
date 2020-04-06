package com.bishopsoft.grip.api.file;

import com.bishopsoft.grip.api.infrastructure.model.UploadTypeEnum;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Data
public class FileUploadBindingModel {
    private MultipartFile file;
    private UploadTypeEnum type;
    private Optional<Long> projectId;
    private Optional<Long> groupId;
}

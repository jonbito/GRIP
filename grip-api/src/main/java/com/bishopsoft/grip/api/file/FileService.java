package com.bishopsoft.grip.api.file;

import com.bishopsoft.grip.api.infrastructure.exception.HttpException;
import com.bishopsoft.grip.api.infrastructure.model.Group;
import com.bishopsoft.grip.api.infrastructure.model.Project;
import com.bishopsoft.grip.api.infrastructure.model.RoleEnum;
import com.bishopsoft.grip.api.infrastructure.model.Upload;
import com.bishopsoft.grip.api.infrastructure.model.UploadTypeEnum;
import com.bishopsoft.grip.api.infrastructure.model.UserAccount;
import com.bishopsoft.grip.api.infrastructure.repository.UploadRepository;
import com.bishopsoft.grip.api.infrastructure.security.LoggedInUser;
import com.bishopsoft.grip.api.infrastructure.service.FirebaseService;
import com.bishopsoft.grip.api.permission.PermissionService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
public class FileService {

    private final UploadRepository uploadRepository;
    private final FirebaseService firebaseService;
    private final PermissionService permissionService;
    private final LoggedInUser loggedInUser;
    private final EntityManager entityManager;

    public FileService(UploadRepository uploadRepository, FirebaseService firebaseService, PermissionService permissionService, LoggedInUser loggedInUser, EntityManager entityManager) throws IOException {
        this.uploadRepository = uploadRepository;
        this.firebaseService = firebaseService;
        this.permissionService = permissionService;
        this.loggedInUser = loggedInUser;
        this.entityManager = entityManager;
    }

    public FileDto get(UUID id) {
        Upload upload = uploadRepository.findById(id).orElseThrow(() -> new HttpException("Upload not found", HttpStatus.BAD_REQUEST));
        checkPermissionsOnExisting(upload);
        FileDto dto = new FileDto();
        dto.setType(upload.getFileType());
        dto.setData(firebaseService.getFromStorage(id.toString() + "." + upload.getFileExtension()));
        return dto;
    }

    @Transactional
    public void delete(UUID id) {
        Upload file = uploadRepository.findById(id).orElseThrow(() -> new HttpException("File not found", HttpStatus.BAD_REQUEST));
        checkPermissionsOnExisting(file);
        uploadRepository.delete(file);
        firebaseService.delete(id.toString() + "." + file.getFileExtension());
    }

    @Transactional
    public UUID upload(MultipartFile file, UploadTypeEnum uploadType, Optional<Long> projectId, Optional<Long> groupId) throws IOException {
        Upload upload;
        switch (uploadType) {
            case USER_AVATAR:
                UserAccount user = entityManager.getReference(UserAccount.class, loggedInUser.getId());
                upload = Optional.of(user.getAvatar()).orElse(new Upload());
                upload.setUser(user);
                break;
            case PROJECT_AVATAR:
                projectId.orElseThrow(() -> new HttpException("Project Id required", HttpStatus.BAD_REQUEST));
                permissionService.assertProjectRoleForLoggedInUser(projectId.get(), RoleEnum.MAINTAINER);
                Project project = entityManager.getReference(Project.class, projectId.get());
                upload = Optional.of(project.getAvatar()).orElse(new Upload());
                upload.setProject(project);
                break;
            case GROUP_AVATAR:
                groupId.orElseThrow(() -> new HttpException("Group Id required", HttpStatus.BAD_REQUEST));
                permissionService.assertGroupRoleForLoggedInUser(groupId.get(), RoleEnum.OWNER);
                Group group = entityManager.getReference(Group.class, groupId.get());
                upload = Optional.of(group.getAvatar()).orElse(new Upload());
                upload.setGroup(group);
                break;
            default:
                throw new HttpException("Upload type doesn't exist", HttpStatus.BAD_REQUEST);
        }

        upload.setUploadType(uploadType);
        upload.setFileType(file.getContentType());
        upload.setFileName(FilenameUtils.getBaseName(file.getOriginalFilename()));
        upload.setFileExtension(FilenameUtils.getExtension(file.getOriginalFilename()));
        uploadRepository.save(upload);

        firebaseService.uploadToStorage(upload.getId().toString() + "." + upload.getFileExtension(), file.getInputStream());

        return upload.getId();
    }

    private void checkPermissionsOnExisting(Upload upload) {
        switch (upload.getUploadType()) {
            case USER_AVATAR:
                if(upload.getUser().getId() != loggedInUser.getId()) {
                    throw new HttpException("Not Authorized", HttpStatus.UNAUTHORIZED);
                }
                break;
            case GROUP_AVATAR:
                permissionService.assertGroupRoleForLoggedInUser(upload.getGroup().getId(), RoleEnum.OWNER);
                break;
            case PROJECT_AVATAR:
                permissionService.assertProjectRoleForLoggedInUser(upload.getProject().getId(), RoleEnum.MAINTAINER);
                break;
            default:
                throw new HttpException("Upload type doesn't exist", HttpStatus.BAD_REQUEST);
        }
    }

    public void uploadUserAvatar(UserAccount user, MultipartFile file) {

        BufferedImage resizedImage;
        try {
            BufferedImage originalImage = ImageIO.read(file.getInputStream());
            resizedImage = resize(originalImage, 64, 64);
        } catch (IOException e) {
            throw new HttpException("Unable to read image", HttpStatus.BAD_REQUEST);
        }

        Upload upload = new Upload();
        upload.setUploadType(UploadTypeEnum.USER_AVATAR);
        upload.setFileType(file.getContentType());
        upload.setFileName(file.getOriginalFilename());
        upload.setUser(user);
        uploadRepository.save(upload);

        // upload to
        String extension = FilenameUtils.getExtension(upload.getFileName());


        // TODO: Finish this method
    }

    private BufferedImage resize(BufferedImage img, int width, int height) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
}

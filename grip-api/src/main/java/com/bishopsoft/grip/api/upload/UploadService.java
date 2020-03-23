package com.bishopsoft.grip.api.upload;

import com.bishopsoft.grip.api.infrastructure.exception.HttpException;
import com.bishopsoft.grip.api.infrastructure.model.Upload;
import com.bishopsoft.grip.api.infrastructure.model.UploadTypeEnum;
import com.bishopsoft.grip.api.infrastructure.repository.UploadRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

@Service
public class UploadService {

    private final Path rootPath;

    private final UploadRepository uploadRepository;

    public UploadService(UploadRepository uploadRepository, @Value("${uploads.rootPath}") final String rootPathString) throws IOException {
        this.uploadRepository = uploadRepository;
        this.rootPath = Paths.get(rootPathString);
        this.init();
    }

    public void uploadUserAvatar(UUID userId, MultipartFile file) {

        BufferedImage resizedImage;
        try {
            BufferedImage originalImage = ImageIO.read(file.getInputStream());
            resizedImage = resize(originalImage, 64, 64);
        } catch (IOException e) {
            throw new HttpException("Unable to read image", HttpStatus.BAD_REQUEST);
        }

        Upload upload = new Upload();
        upload.setUploadType(UploadTypeEnum.USER_AVATAR);
        upload.setReferencedId(userId.toString());
        upload.setFileType(file.getContentType());
        upload.setFileName(file.getOriginalFilename());
        uploadRepository.save(upload);

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

    public void store(MultipartFile file) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                throw new IOException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new IOException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, this.rootPath.resolve(filename),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (IOException ignored) {
        }
    }

    public String getUserAvatar(UUID userId) {
        Optional<Upload> uploadOptional = uploadRepository.findByUploadTypeAndReferencedId(UploadTypeEnum.USER_AVATAR, userId.toString());
        if(uploadOptional.isEmpty()) {
            return null;
        }

        try {
            Resource resource = loadAsResource(uploadOptional.get().getFileName());
            return Base64.getEncoder().encodeToString(resource.getInputStream().readAllBytes());
        } catch (Exception e) {
            return null;
        }
    }

    private Resource loadAsResource(String filename) throws IOException {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new IOException(
                        "Could not read file: " + filename);
            }
        }
        catch (MalformedURLException e) {
            throw new IOException("Could not read file: " + filename, e);
        }
    }

    private Path load(String filename) {
        return rootPath.resolve(filename);
    }

    private void deleteAll() {
        FileSystemUtils.deleteRecursively(rootPath.toFile());
    }

    public void init() throws IOException {
        try {
            Files.createDirectories(rootPath);
        }
        catch (IOException e) {
            throw new IOException("Could not initialize storage", e);
        }
    }
}

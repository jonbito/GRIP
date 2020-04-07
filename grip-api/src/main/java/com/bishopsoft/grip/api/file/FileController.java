package com.bishopsoft.grip.api.file;

import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Consumes;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/file")
public class FileController {
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("{id}")
    public ResponseEntity<byte[]> get(@PathVariable UUID id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
        FileDto fileDto = fileService.get(id);
        headers.setContentType(MediaType.parseMediaType(fileDto.getType()));
        return new ResponseEntity<>(fileDto.getData(), headers, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        fileService.delete(id);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @PostMapping
    @Consumes(MediaType.MULTIPART_FORM_DATA_VALUE)
    public UUID upload(FileUploadBindingModel fileUpload) throws IOException {
        return fileService.upload(fileUpload.getFile(), fileUpload.getType(), fileUpload.getProjectId(), fileUpload.getGroupId());
    }
}

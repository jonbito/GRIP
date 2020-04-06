package com.bishopsoft.grip.api.file;

import lombok.Data;

@Data
public class FileDto {
    private String type;
    private byte[] data;
}

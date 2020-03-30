package com.bishopsoft.grip.api.infrastructure.dto;

import lombok.Data;

import java.util.List;

@Data
public class ListPageDTO<T> {
    private List<T> contents;
    private long total;
}

package com.bishopsoft.grip.api.infrastructure.exception;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ValidationErrorResponse {
    String field;
    List<String> errors = new ArrayList<>();
}

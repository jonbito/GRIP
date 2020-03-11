package com.bishopsoft.grip.api.exception;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ValidationErrorResponse {
    String field;
    List<String> errors = new ArrayList<>();
}

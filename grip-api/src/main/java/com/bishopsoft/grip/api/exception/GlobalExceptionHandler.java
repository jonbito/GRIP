package com.bishopsoft.grip.api.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());

        Set<ValidationErrorResponse> errors = new HashSet<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(f -> {
                    ValidationErrorResponse validationErrorResponse = errors.stream().filter(e -> e.getField().equals(f.getField())).findFirst().orElse(null);
                    if(validationErrorResponse == null) {
                        validationErrorResponse = new ValidationErrorResponse();
                        validationErrorResponse.setField(f.getField());
                        validationErrorResponse.getErrors().add(f.getDefaultMessage());
                        errors.add(validationErrorResponse);
                    } else {
                        validationErrorResponse.getErrors().add(f.getDefaultMessage());
                    }
                });

        body.put("errors", errors);

        return new ResponseEntity<>(body, headers, status);
    }

    @ExceptionHandler(HttpException.class)
    public void httpExceptionHandler(HttpException e, HttpServletResponse response) throws IOException {
        response.sendError(e.getHttpStatus().value(), e.getMessage());
    }
}

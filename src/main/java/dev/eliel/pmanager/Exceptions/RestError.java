package dev.eliel.pmanager.Exceptions;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RestError {
    private final String errorMessage;
    private final int status;
    private final String path;
    private final List<FieldError> fieldErrors;

    @Data
    @Builder
    public static class FieldError{
        private final String field;
        private final String message;
    }
}


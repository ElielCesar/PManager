package dev.eliel.pmanager.domain.infraestructure.exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
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


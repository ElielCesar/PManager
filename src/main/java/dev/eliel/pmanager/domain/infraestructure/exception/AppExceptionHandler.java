package dev.eliel.pmanager.domain.infraestructure.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class AppExceptionHandler {

    // Erros de validação (@NotBlank, @NotNull, etc.)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestError> handleValidationErrors(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        List<RestError.FieldError> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> RestError.FieldError.builder()
                        .field(error.getField())
                        .message(error.getDefaultMessage())
                        .build())
                .toList();

        RestError restError = RestError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .errorMessage("Um ou mais campos estão inválidos")
                .path(request.getRequestURI())
                .fieldErrors(fieldErrors)
                .build();

        return ResponseEntity.badRequest().body(restError);
    }

    // Erros de negócio (RuntimeException)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<RestError> handleRuntimeException(
            RuntimeException ex,
            HttpServletRequest request) {

        RestError restError = RestError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .errorMessage(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.badRequest().body(restError);
    }

}

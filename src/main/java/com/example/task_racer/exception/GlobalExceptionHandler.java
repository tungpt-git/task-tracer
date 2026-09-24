package com.example.task_racer.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.example.task_racer.model.dto.ErrorResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<ErrorResponseDto> handleResourceNotFound(
                        ResourceNotFoundException ex,
                        WebRequest request) {
                return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage(), request, null);
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ErrorResponseDto> handleValidationErrors(
                        MethodArgumentNotValidException ex,
                        WebRequest request) {
                Map<String, String> fieldErrors = new HashMap<>();
                ex.getBindingResult().getFieldErrors()
                                .forEach(error -> fieldErrors.put(error.getField(), error.getDefaultMessage()));

                return buildResponse(
                                HttpStatus.BAD_REQUEST,
                                "Validation failed",
                                request,
                                fieldErrors);
        }

        @ExceptionHandler(MethodArgumentTypeMismatchException.class)
        public ResponseEntity<ErrorResponseDto> handleTypeMismatch(
                        MethodArgumentTypeMismatchException ex,
                        WebRequest request) {
                String message = String.format("Invalid value '%s' for parameter '%s'",
                                ex.getValue(), ex.getName());
                return buildResponse(HttpStatus.BAD_REQUEST, message, request, null);
        }

        @ExceptionHandler(HttpMessageNotReadableException.class)
        public ResponseEntity<ErrorResponseDto> handleUnreadableMessage(
                        HttpMessageNotReadableException ex,
                        WebRequest request) {
                return buildResponse(HttpStatus.BAD_REQUEST, "Malformed JSON request", request, null);
        }

        @ExceptionHandler(IllegalArgumentException.class)
        public ResponseEntity<ErrorResponseDto> handleIllegalArgument(
                        IllegalArgumentException ex,
                        WebRequest request) {
                return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request, null);
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ErrorResponseDto> handleGenericException(
                        Exception ex,
                        WebRequest request) {

                System.out.println("APPLICATION Exception: " + ex);

                return buildResponse(
                                HttpStatus.INTERNAL_SERVER_ERROR,
                                "An unexpected error occurred",
                                request,
                                null);
        }

        private ResponseEntity<ErrorResponseDto> buildResponse(
                        HttpStatus status,
                        String message,
                        WebRequest request,
                        Map<String, String> fieldErrors) {
                ErrorResponseDto body = ErrorResponseDto.builder()
                                .status(status.value())
                                .error(status.getReasonPhrase())
                                .message(message)
                                .path(request.getDescription(false).replace("uri=", ""))
                                .timestamp(LocalDateTime.now())
                                .fieldErrors(fieldErrors)
                                .build();

                return ResponseEntity.status(status).body(body);
        }
}

package com.vault.devfood.api.exceptionhandler;


import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.vault.devfood.domain.exception.EntityInUseException;
import com.vault.devfood.domain.exception.ViolationBusinessRulesException;

import jakarta.persistence.EntityNotFoundException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException exception, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.ENTITY_NOT_FOUND;
        String detail = exception.getMessage();
        ResponseError body = createResponseError(status, problemType, detail).build();

        return this.handleExceptionInternal(exception, body, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntityInUseException.class)
    public ResponseEntity<Object> handleEntityInUseException(EntityInUseException exception, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        ProblemType problemType = ProblemType.ENTITY_IN_USE;
        String detail = exception.getMessage();
        ResponseError body = createResponseError(status, problemType, detail).build();

        return this.handleExceptionInternal(exception, body, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException exception, WebRequest request) {

        HttpStatus status = HttpStatus.CONFLICT;
        ProblemType problemType = ProblemType.ENTITY_IN_USE;
        String detail = exception.getMessage();
        ResponseError body = createResponseError(status, problemType, detail).build();

        return this.handleExceptionInternal(exception, body, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(ViolationBusinessRulesException.class)
    public ResponseEntity<Object> handleBusinessException(ViolationBusinessRulesException exception, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.ERROR_BUSINESS;
        String detail = exception.getMessage();
        ResponseError body = createResponseError(status, problemType, detail).build();

        return this.handleExceptionInternal(exception, body, new HttpHeaders(), status, request);
    }


    @Nullable
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Throwable rootCause = ExceptionUtils.getRootCause(ex);

        if (rootCause instanceof InvalidFormatException){
            return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
        } else if (rootCause instanceof PropertyBindingException){

        }

        ProblemType problemType = ProblemType.MESSAGE_NOT_READABLE;
        String detail = "The request body is invalid. Check syntax errors.";
        ResponseError body = createResponseError(HttpStatus.valueOf(status.value()), problemType, detail).build();

        return this.handleExceptionInternal(ex, body, new HttpHeaders(), status, request);
    }

    private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        String path = ex.getPath().stream()
            .map(JsonMappingException.Reference::getFieldName)
            .collect(Collectors.joining("."));

        ProblemType problemType = ProblemType.MESSAGE_NOT_READABLE;
        String detail = String.format("The property '%s' was assigned the value '%s', which is of an invalid type. " +
        "Correct and enter a value compatible with the type '%s'",
            path, ex.getValue(), ex.getTargetType().getSimpleName());

        ResponseError body = createResponseError(HttpStatus.valueOf(status.value()), problemType, detail).build();

        return this.handleExceptionInternal(ex, body, new HttpHeaders(), status, request);
    }

    @Nullable
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {

        if (body == null) {
            body = ResponseError
                .builder()
                .title(HttpStatus.valueOf(statusCode.value()).getReasonPhrase())
                .status(statusCode.value())
                .build();
        } else if (body instanceof String) {
            body = ResponseError
                .builder()
                .title((String) body)
                .status(statusCode.value())
                .build();
        }

        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }


    private ResponseError.ResponseErrorBuilder createResponseError(HttpStatus status, ProblemType type, String detail) {
        return ResponseError
            .builder()
            .status(status.value())
            .type(type.getUri())
            .title(type.getTitle())
            .detail(detail);

    }

}

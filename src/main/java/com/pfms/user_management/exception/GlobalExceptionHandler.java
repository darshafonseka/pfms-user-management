package com.pfms.user_management.exception;

import com.pfms.user_management.enums.ErrorType;
import com.pfms.user_management.enums.Severity;
import com.pfms.user_management.model.UserManagementApiResponse;
import com.pfms.user_management.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<UserManagementApiResponse<GlobalExceptionResponse>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){

        List<ValidationException> details = new ArrayList<>();

        for(FieldError fieldError : exception.getBindingResult().getFieldErrors()){
            details.add(new ValidationException(fieldError.getField(), fieldError.getDefaultMessage()));
        }

        GlobalExceptionResponse response = new GlobalExceptionResponse();
        response.setErrorType(ErrorType.VALIDATION_ERROR.name());
        response.setValidationException(details);
        response.setSeverity(Severity.LOW.name());

        return ResponseUtil.badRequest(response);
    }

    @ExceptionHandler(ApplicationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<UserManagementApiResponse<GlobalExceptionResponse>> handleApplicationException(ApplicationException exception){

        GlobalExceptionResponse response = new GlobalExceptionResponse();
        response.setErrorType(ErrorType.APPLICATION_ERROR.name());
        response.setApplicationException(exception);
        response.setSeverity(exception.getSeverity().name());

        return ResponseUtil.badRequest(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<UserManagementApiResponse<GlobalExceptionResponse>> handleHttpMessageNotReadable(HttpMessageNotReadableException exception) {
        List<ValidationException> details = new ArrayList<>();

        Throwable rootCause = exception.getRootCause();
        String errorMessage = "Invalid input data";

        if (rootCause instanceof IllegalArgumentException) {
            errorMessage = rootCause.getMessage();
        }

        details.add(new ValidationException("input", errorMessage));

        GlobalExceptionResponse response = new GlobalExceptionResponse();
        response.setErrorType(ErrorType.VALIDATION_ERROR.name());
        response.setValidationException(details);
        response.setSeverity(Severity.LOW.name());

        return ResponseUtil.badRequest(response);
    }

}

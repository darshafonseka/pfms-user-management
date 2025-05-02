package com.pfms.user_management.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pfms.user_management.enums.Severity;
import com.pfms.user_management.enums.UserManagementError;
import lombok.Getter;
import lombok.ToString;

@Getter
@JsonIgnoreProperties({"cause" , "message" , "stackTrace" , "suppressed" , "localizedMessage" , "severity"})
@ToString
public class ApplicationException extends RuntimeException {

    private final String errorCode;
    private final String errorMessage;
    private final Severity severity;

    public ApplicationException(UserManagementError userManagementError) {
        this.errorCode = userManagementError.getErrorCode();
        this.errorMessage = userManagementError.getErrorMessage();
        this.severity = userManagementError.getSeverity();
    }

}

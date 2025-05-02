package com.pfms.user_management.enums;

import lombok.Getter;

@Getter
public enum UserManagementError {
    USER_NOT_FOUND("001", "User not found", Severity.LOW),
    USER_EXIST("002", "User already exists", Severity.LOW),
    USER_ROLE_NOT_FOUND("003", "User role not found", Severity.LOW),
    USER_ROLE_EXIST("004", "User role already exists", Severity.LOW),
    RECORD_NOT_FOUND("005", "No record found", Severity.LOW);

    private final String errorCode;
    private final String errorMessage;
    private final Severity severity;

    UserManagementError(String errorCode, String errorMessage, Severity severity) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.severity = severity;
    }
}

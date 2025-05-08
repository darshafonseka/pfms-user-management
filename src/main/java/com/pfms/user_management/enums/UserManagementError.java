package com.pfms.user_management.enums;

import lombok.Getter;

@Getter
public enum UserManagementError {
    USER_NOT_FOUND("001", "User not found", Severity.HIGH),
    USER_EXISTS("002", "User already exists", Severity.MEDIUM),
    RECORD_NOT_FOUND("003", "No record found", Severity.LOW),
    CURRENT_PASSWORD_INCORRECT("004", "Current password is incorrect", Severity.HIGH),
    NEW_PASSWORD_SAME_AS_CURRENT_PASSWORD("007", "New password is same as current password", Severity.HIGH),
    UNAUTHORIZED_ACCESS("005", "Unauthorized access", Severity.HIGH),
    INCORRECT_PASSWORD("006", "Incorrect password", Severity.HIGH),
    USER_STATUS_ALREADY_UPDATED("007", "No update performed: current user status matches the requested status", Severity.LOW);

    private final String errorCode;
    private final String errorMessage;
    private final Severity severity;

    UserManagementError(String errorCode, String errorMessage, Severity severity) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.severity = severity;
    }
}

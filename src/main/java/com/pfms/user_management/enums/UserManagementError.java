package com.pfms.user_management.enums;

import lombok.Getter;

@Getter
public enum UserManagementError {
    USER_NOT_FOUND("001", "User not found", Severity.HIGH),
    USER_EXIST("002", "User already exists", Severity.MEDIUM),
    RECORD_NOT_FOUND("005", "No record found", Severity.LOW),
    CURRENT_PASSWORD_INCORRECT("006", "Current password is incorrect", Severity.HIGH),
    NEW_PASSWORD_SAME_AS_CURRENT_PASSWORD("007", "New password is same as current password", Severity.HIGH),
    UNAUTHORIZED_ACCESS("008", "Unauthorized access", Severity.HIGH),
    INCORRECT_PASSWORD("009", "Incorrect password", Severity.HIGH),
    USER_STATUS_ALREADY_UPDATED("010", "User status already updated", Severity.LOW);

    private final String errorCode;
    private final String errorMessage;
    private final Severity severity;

    UserManagementError(String errorCode, String errorMessage, Severity severity) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.severity = severity;
    }
}

package com.pfms.user_management.constants;

public class ValidationConstants {
    private ValidationConstants() {
        // Hides the implicit public constructor
    }

    public static final String PASSWORD_PATTERN =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$";

    public static final String PASSWORD_MESSAGE =
            "Password must be at least 8 characters long, include uppercase, lowercase, a number, and a special character.";
}

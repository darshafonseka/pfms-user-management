package com.pfms.user_management.request;

import com.pfms.user_management.constants.ValidationConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "Password Reset Request", description = "Password Reset Request")
public class PasswordResetRequest {
    @Column(name = "current_password", nullable = false)
    @NotEmpty(message = "Current password required")
    @Schema(description = "Current password",  example = "password")
    private String currentPassword;

    @Pattern(
            regexp = ValidationConstants.PASSWORD_PATTERN,
            message = ValidationConstants.PASSWORD_MESSAGE
    )
    @NotEmpty(message = "New password required")
    @Schema(description = "New password",  example = "password")
    @Column(name = "new_password", nullable = false)
    private String newPassword;
}

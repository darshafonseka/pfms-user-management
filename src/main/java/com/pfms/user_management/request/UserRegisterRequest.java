package com.pfms.user_management.request;

import com.pfms.user_management.constants.ValidationConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Register Request DTO")
public class UserRegisterRequest {

    @Column(name = "username", nullable = false)
    @NotEmpty(message = "Username required")
    @Schema(description = "Username",  example = "LmsUser")
    private String username;

    @Column(name = "email", unique = true, nullable = false)
    @Email(message = "Enter valid email")
    @NotEmpty(message = "Email address required")
    @Schema(description = "Email address",  example = "lms@example.com")
    private String email;

    @Column(name = "password", nullable = false)
    @NotEmpty(message = "Password required")
    @Size(min = 8, message = "Password should be at least 8 characters")
    @Pattern(
            regexp = ValidationConstants.PASSWORD_PATTERN,
            message = ValidationConstants.PASSWORD_MESSAGE
    )
    @Schema(description = "Password",  example = "password")
    private String password;

    @Column(name = "role")
    @Schema(description = "Role", example = "USER")
    private String role;
}
